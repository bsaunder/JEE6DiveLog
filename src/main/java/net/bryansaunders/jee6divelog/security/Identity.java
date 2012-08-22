/**
 * 
 */
package net.bryansaunders.jee6divelog.security;

/*
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

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;
import net.bryansaunders.jee6divelog.service.UserAccountService;
import net.bryansaunders.jee6divelog.util.SecurityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User Identity implements Serializable.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@SessionScoped
@Named("identity")
@XmlRootElement
public class Identity implements Serializable {

    /**
     * Login Failure.
     */
    public static final boolean LOGIN_FAILURE = false;

    /**
     * Login Success.
     */
    public static final boolean LOGIN_SUCCESS = true;

    /**
     * Logout Failure.
     */
    public static final String LOGOUT_FAILURE = "/logout_error";

    /**
     * Logout Success.
     */
    public static final String LOGOUT_SUCCESS = "/logout_success";

    /**
     * Identity Logged Out.
     */
    public static final Integer LOGGED_OUT = 1;

    /**
     * Identity Logged In.
     */
    public static final Integer LOGGED_IN = 0;

    /**
     * Default Serial ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(Identity.class);

    /**
     * User Entered Credentials.
     */
    @Inject
    @XmlTransient
    private Credentials credentials;

    /**
     * User Account Service.
     */
    @Inject
    @XmlTransient
    private UserAccountService userAccountService;

    /**
     * Should the user be Remembered.
     */
    private boolean rememberMe;

    /**
     * Identity Permissions.
     */
    private Set<Permission> permissions;

    /**
     * User Public API Key.
     */
    private String publicApiKey;
    
    /**
     * User Private API Key.
     */
    private String privateApiKey;

    /**
     * User API Key Expiration Date.
     */
    private Date apiKeyExpiration;

    /**
     * Identity Status.
     */
    private Integer status;

    /**
     * Default Constructor.
     */
    public Identity() {
        this.permissions = new LinkedHashSet<Permission>();
        this.status = Identity.LOGGED_OUT;
    }

    /**
     * Login Identity.
     * 
     * @return Login Result
     */
    public boolean login() {
        return this.login(false);
    }

    /**
     * Login Identity for REST Service.
     * 
     * @return Login Result
     */
    public boolean restLogin() {
        return this.login(true);
    }

    /**
     * Login Identity.
     * 
     * @param restLogin
     *            Is it a REST Login
     * @return Login Result
     */
    public boolean login(final boolean restLogin) {
        this.logger.debug("Logging In User with Credentials: " + this.credentials);
        boolean returnValue = Identity.LOGIN_FAILURE;

        final String username = this.getUsername();
        final String password = this.getPassword();
        final String expectedPassword = SecurityUtils.generatePasswordHash(password);

        final UserAccount userAccount = this.userAccountService.findByUserEmail(username);
        if (userAccount != null) {
            final String hashedPassword = userAccount.getPassword();
            if (hashedPassword.equals(expectedPassword)) {
                this.setStatus(Identity.LOGGED_IN);

                this.setPermissions(userAccount.getPermissions());

                if (restLogin) {
                    this.publicApiKey = SecurityUtils.generateRestApiKey();
                    this.privateApiKey = SecurityUtils.generateRestApiKey();

                    final Calendar expiration = Calendar.getInstance();
                    expiration.add(Calendar.HOUR, 5);
                    this.apiKeyExpiration = expiration.getTime();

                    userAccount.setPublicApiKey(this.publicApiKey);
                    userAccount.setPrivateApiKey(this.privateApiKey);
                    userAccount.setApiKeyExpiration(this.apiKeyExpiration);
                    this.userAccountService.saveUser(userAccount);
                }

                this.logger.debug("Logged in User: " + this.credentials);
            }
        }else{
            this.logger.debug("Invalid User: User Not Found");
        }

        if (this.isLoggedIn()) {
            returnValue = Identity.LOGIN_SUCCESS;
        } else {
            this.logger.warn("Invalid Credentials: " + this.credentials);
        }

        return returnValue;
    }

    /**
     * Logout the current User.
     * 
     * @return Logout Result
     */
    public String logout() {
        this.logger.info("Logging Out User with Credentials: " + this.credentials);
        String returnValue = Identity.LOGOUT_FAILURE;

        this.setStatus(Identity.LOGGED_OUT);

        if (!this.isLoggedIn()) {
            returnValue = Identity.LOGOUT_SUCCESS;
        }

        return returnValue;
    }

    /**
     * Is the Identity Logged in?
     * 
     * @return true if logged in
     */
    public boolean isLoggedIn() {
        return this.status.equals(Identity.LOGGED_IN);
    }

    /**
     * Get the status.
     * 
     * @return the status
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * Set the status.
     * 
     * @param newStatus
     *            the status to set
     */
    public void setStatus(final Integer newStatus) {
        this.status = newStatus;
    }

    /**
     * Get Username.
     * 
     * @return username
     */
    public String getUsername() {
        return this.credentials.getUsername();
    }

    /**
     * Get Password.
     * 
     * @return password
     */
    public String getPassword() {
        return this.credentials.getPassword();
    }

    /**
     * Set the credentials.
     * 
     * @param newCredentials
     *            the credentials to set
     */
    public void setCredentials(final Credentials newCredentials) {
        this.credentials = newCredentials;
    }

    /**
     * Set the userAccountService.
     * 
     * @param newUserAccountService
     *            the userAccountService to set
     */
    public void setUserAccountService(final UserAccountService newUserAccountService) {
        this.userAccountService = newUserAccountService;
    }

    /**
     * Get the permissions.
     * 
     * @return the permissions
     */
    public Set<Permission> getPermissions() {
        return this.permissions;
    }

    /**
     * Set the permissions.
     * 
     * @param newPermissions
     *            the permissions to set
     */
    public void setPermissions(final Set<Permission> newPermissions) {
        this.permissions = newPermissions;
    }

    /**
     * Get the rememberMe.
     * 
     * @return the rememberMe
     */
    public boolean isRememberMe() {
        return this.rememberMe;
    }

    /**
     * Set the rememberMe.
     * 
     * @param newRememberMe
     *            the rememberMe to set
     */
    public void setRememberMe(final boolean newRememberMe) {
        this.rememberMe = newRememberMe;
    }

    /**
     * Has the specified Permission.
     * 
     * @param permission
     *            Permission to check for
     * @return true if permission is found
     */
    public boolean hasPermission(final Permission permission) {
        return this.permissions.contains(permission);
    }

    /**
     * Get the Public apiKey.
     * 
     * @return the apiKey
     */
    public String getPublicApiKey() {
        return this.publicApiKey;
    }

    /**
     * Set the Public apiKey.
     * 
     * @param newApiKey
     *            the apiKey to set
     */
    public void setPublicApiKey(final String newApiKey) {
        this.publicApiKey = newApiKey;
    }
    
    /**
     * Get the Private apiKey.
     * 
     * @return the apiKey
     */
    public String getPrivateApiKey() {
        return this.privateApiKey;
    }

    /**
     * Set the Private apiKey.
     * 
     * @param newApiKey
     *            the apiKey to set
     */
    public void setPrivateApiKey(final String newApiKey) {
        this.privateApiKey = newApiKey;
    }

    /**
     * Get the apiKeyExpiration.
     * 
     * @return the apiKeyExpiration
     */
    public Date getApiKeyExpiration() {
        Date date = null;
        if (this.apiKeyExpiration != null) {
            date = new Date(this.apiKeyExpiration.getTime());
        }
        return date;
    }

    /**
     * Set the apiKeyExpiration.
     * 
     * @param newApiKeyExpiration
     *            the apiKeyExpiration to set
     */
    public void setApiKeyExpiration(final Date newApiKeyExpiration) {
        if (newApiKeyExpiration == null) {
            this.apiKeyExpiration = null;
        } else {
            this.apiKeyExpiration = new Date(newApiKeyExpiration.getTime());
        }
    }

}
