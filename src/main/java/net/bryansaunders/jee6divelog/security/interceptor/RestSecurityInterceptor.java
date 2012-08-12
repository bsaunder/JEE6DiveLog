package net.bryansaunders.jee6divelog.security.interceptor;/*
 * #%L
 * BSNet-DiveLog
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 Bryan Saunders
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.Credentials;
import net.bryansaunders.jee6divelog.security.Identity;
import net.bryansaunders.jee6divelog.service.UserAccountService;
import net.bryansaunders.jee6divelog.util.SecurityUtils;

import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
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
@ServerInterceptor
@Precedence("SECURITY")
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

        final boolean isSecured = SecurityUtils.isMethodSecure(resourceMethod);

        if (isSecured) {
            this.logger.debug("Secure API Call: " + httpRequest.getUri().getAbsolutePath());

            userName = this.getUserDefinedHeader(httpRequest, "dl-username");
            userToken = this.getUserDefinedHeader(httpRequest, "dl-token");

            this.logger.debug("Using Headers: userName:" + userName + " | token:" + userToken);

            // Get UserAccount by Username
            if (userName != null && userToken != null) {
                this.logger.debug("REST API Call Headers Valid");
                final UserAccount userAccount = this.userAccountService.findByUserEmail(userName);

                if (userAccount != null) {
                    this.logger.debug("REST API User Found");
                    // Check Expiration Date
                    final Date expirationDate = userAccount.getApiKeyExpiration();

                    if (expirationDate != null && System.currentTimeMillis() < expirationDate.getTime()) {
                        this.logger.debug("API Token Not Expired");
                        // Generate Token
                        final String apiKey = userAccount.getApiKey();
                        final String expectedToken = SecurityUtils.generateRestApiToken(userName, apiKey);

                        // Check Tokens
                        if (expectedToken.equals(userToken)) {
                            this.logger.debug("API Token Valid");

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
                            
                            // Return null
                            response = null;

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
     * Build a ServerResponse Message.
     * 
     * @param statusCode
     *            Response Status
     * @param message
     *            Response Message
     * @return ServerResponse
     */
    private ServerResponse buildResponse(final int statusCode, final String message) {
        this.logger.info("Creating REST Response: " + statusCode + " - " + message);
        final ServerResponse response = new ServerResponse();
        response.setStatus(statusCode);
        response.setEntity(message);
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
        if (headerList != null && !headerList.isEmpty()) {
            headerValue = headerList.get(0);
        }

        return headerValue;
    }

}
