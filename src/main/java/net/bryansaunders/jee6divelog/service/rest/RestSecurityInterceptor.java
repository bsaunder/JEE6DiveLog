package net.bryansaunders.jee6divelog.service.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.Credentials;
import net.bryansaunders.jee6divelog.security.Identity;
import net.bryansaunders.jee6divelog.security.annotation.HasPermission;
import net.bryansaunders.jee6divelog.security.annotation.HasPermissions;
import net.bryansaunders.jee6divelog.security.annotation.HasRole;
import net.bryansaunders.jee6divelog.security.annotation.HasRoles;
import net.bryansaunders.jee6divelog.service.UserAccountService;
import net.bryansaunders.jee6divelog.util.SecurityUtils;

import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.jboss.resteasy.util.HttpResponseCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Security Intercepter for REST API Calls.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class RestSecurityInterceptor implements PreProcessInterceptor {

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(RestSecurityInterceptor.class);

    /**
     * Identity for current Session.
     */
    @Inject
    private Identity identity;

    /**
     * User Account Service.
     */
    @Inject
    private UserAccountService userAccountService;

    /**
     * Called before any REST service calls are processed.
     * 
     * @param httpRequest
     *            Initial HTTP Request
     * @param resourceMethod
     *            Resource Method being Called
     * @return Response for the Server, Null if the API call should proceed
     * @throws Failure
     *             Thrown on Failure
     * @throws WebApplicationException
     *             Thrown on Error
     */
    @Override
    public ServerResponse preProcess(final HttpRequest httpRequest, final ResourceMethod resourceMethod)
            throws Failure, WebApplicationException {
        ServerResponse response = null;
        String userName = null;
        String userToken = null;

        this.logger.info("REST API Called: " + httpRequest.getUri().getAbsolutePath());

        final boolean isSecured = isMethodSecure(resourceMethod);

        if (isSecured) {
            this.logger.info("Checking Security for REST API Call");

            userName = this.getUserDefinedHeader(httpRequest, "dl-username");
            userToken = this.getUserDefinedHeader(httpRequest, "dl-token");

            this.logger.info("Using Headers: userName:" + userName + " | token:" + userToken);

            // Get UserAccount by Username
            if (userName != null && userToken != null) {
                final UserAccount userAccount = this.userAccountService.findByUserEmail(userName);
                
                if (userAccount != null) {
                    // Check Expiration Date
                    final Date expirationDate = userAccount.getApiKeyExpiration();
                    
                    if (System.currentTimeMillis() < expirationDate.getTime()) {
                        // Generate Token
                        final String apiKey = userAccount.getApiKey();
                        final String expectedToken = SecurityUtils.generateRestApiToken(userName, apiKey);
                        
                        // Check Tokens
                        if (expectedToken.equals(userToken)) {
                            // Set Identity
                            this.identity.setApiKey(apiKey);
                            this.identity.setApiKeyExpiration(expirationDate);
                            this.identity.setPermissions(userAccount.getPermissions());
                            this.identity.setRoles(userAccount.getRoles());
                            this.identity.setStatus(Identity.LOGGED_IN);
                            final Credentials credentials = new Credentials();
                            credentials.setUsername(userName);
                            credentials.setPassword(userAccount.getPassword());
                            this.identity.setCredentials(credentials);

                        } else {
                            response = this.buildResponse(HttpResponseCodes.SC_UNAUTHORIZED, "Invalid Token");
                        }
                    } else {
                        response = this.buildResponse(HttpResponseCodes.SC_UNAUTHORIZED, "Token Expired");
                    }
                } else {
                    response = this.buildResponse(HttpResponseCodes.SC_UNAUTHORIZED, "Invalid User");
                }
            } else {
                response = this.buildResponse(HttpResponseCodes.SC_BAD_REQUEST, "Missing Headers");
            }
        }

        return response;
    }

    /**
     * Determines if the Method is Secure or Not based on its Declared Annotations.
     * 
     * @param resourceMethod
     *            Called Method
     * @return true if the Method is Secured
     */
    private boolean isMethodSecure(final ResourceMethod resourceMethod) {
        boolean isSecured = false;
        final Method method = resourceMethod.getMethod();
        final Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        for (final Annotation annotation : declaredAnnotations) {
            if (this.isAnnotationSecure(annotation)) {
                isSecured = true;
                break;
            }

        }
        return isSecured;
    }

    /**
     * Determines is the given Annotation is Secured.
     * 
     * @param annotation
     *            Annotation to check
     * @return true if the annotation is secured, false if it is not
     */
    private boolean isAnnotationSecure(final Annotation annotation) {
        boolean isSecured = false;
        if (annotation instanceof HasPermission) {
            isSecured = true;
        } else if (annotation instanceof HasPermissions) {
            isSecured = true;
        } else if (annotation instanceof HasRole) {
            isSecured = true;
        } else if (annotation instanceof HasRoles) {
            isSecured = true;
        }
        return isSecured;
    }

    /**
     * Build a ServerResponse Message.
     * 
     * @param statusCode
     *            Response Status
     * @param message
     *            Response Message
     * @return ServerResponse
     */
    private ServerResponse buildResponse(final int statusCode, final String message) {
        final ServerResponse response = new ServerResponse();
        response.setStatus(HttpResponseCodes.SC_BAD_REQUEST);
        response.setEntity("Missing Headers.");
        return response;
    }

    /**
     * Gets a User Defined Header.
     * 
     * @param httpRequest
     *            HTTP Request with Headers
     * @param headerName
     *            Name of User Defined Header
     * @return Header Value if Found, null if not found
     */
    private String getUserDefinedHeader(final HttpRequest httpRequest, final String headerName) {
        String headerValue = null;
        final HttpHeaders headers = httpRequest.getHttpHeaders();

        final List<String> headerList = headers.getRequestHeader(headerName);
        if (headerList != null && headerList.size() > 0) {
            headerValue = headerList.get(0);
        }

        return headerValue;
    }

}
