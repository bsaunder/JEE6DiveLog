package net.bryansaunders.jee6divelog.service.rest;

import java.lang.annotation.Annotation;
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

        boolean isSecured = false;
        Annotation[] declaredAnnotations = resourceMethod.getMethod().getDeclaredAnnotations();
        for (Annotation annotation : declaredAnnotations) {
            if (annotation instanceof HasPermission) {
                isSecured = true;
            } else if (annotation instanceof HasPermissions) {
                isSecured = true;
            } else if (annotation instanceof HasRole) {
                isSecured = true;
            } else if (annotation instanceof HasRoles) {
                isSecured = true;
            }
        }

        if (isSecured) {
            this.logger.info("Checking Security for REST API Call");
            final HttpHeaders headers = httpRequest.getHttpHeaders();

            final List<String> userNameList = headers.getRequestHeader("dl-username");
            if (userNameList != null && userNameList.size() > 0) {
                userName = userNameList.get(0);
            }

            final List<String> userTokenList = headers.getRequestHeader("dl-token");
            if (userTokenList != null && userTokenList.size() > 0) {
                userToken = userTokenList.get(0);
            }

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
                            // TODO Refactor This Section
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
                            response = new ServerResponse();
                            response.setStatus(HttpResponseCodes.SC_UNAUTHORIZED);
                            response.setEntity("Invalid Token.");
                        }
                    } else {
                        response = new ServerResponse();
                        response.setStatus(HttpResponseCodes.SC_UNAUTHORIZED);
                        response.setEntity("Token Expired.");
                    }
                } else {
                    response = new ServerResponse();
                    response.setStatus(HttpResponseCodes.SC_UNAUTHORIZED);
                    response.setEntity("Invalid User.");
                }
            } else {
                response = new ServerResponse();
                response.setStatus(HttpResponseCodes.SC_BAD_REQUEST);
                response.setEntity("Missing Headers.");
            }
        }

        return response;
    }

}
