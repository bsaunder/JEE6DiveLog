/**
 * 
 */
package net.bryansaunders.jee6divelog.security;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.enums.Permission;
import net.bryansaunders.jee6divelog.security.enums.Role;
import net.bryansaunders.jee6divelog.service.UserAccountService;
import net.bryansaunders.jee6divelog.utils.SecurityUtils;

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
    private Credentials credentials;

    /**
     * User Account Service.
     */
    @Inject
    private UserAccountService userAccountService;

    /**
     * Should the user be Remembered.
     */
    private boolean rememberMe;

    /**
     * Identity Roles.
     */
    private List<Role> roles;

    /**
     * Identity Permissions.
     */
    private List<Permission> permissions;

    /**
     * Identity Status.
     */
    private Integer status;

    /**
     * Default Constructor.
     */
    public Identity() {
        this.roles = new LinkedList<Role>();
        this.permissions = new LinkedList<Permission>();
        this.status = Identity.LOGGED_OUT;
    }

    /**
     * Login Identity.
     * 
     * @return Login Result
     */
    public boolean login() {
        this.logger.info("Logging In User with Credentials: " + this.credentials);
        boolean returnValue = Identity.LOGIN_FAILURE;

        final String username = this.getUsername();
        final String password = this.getPassword();
        final String expectedPassword = SecurityUtils.generatePasswordHash(password);

        final UserAccount userAccount = this.userAccountService.findByUserEmail(username);
        if (userAccount != null) {
            final String hashedPassword = userAccount.getPassword();
            if (hashedPassword.equals(expectedPassword)) {
                this.setStatus(Identity.LOGGED_IN);

                // Set Roles
                // Set Permissions

                this.logger.info("Logged in User: " + this.credentials);
            }
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
     * Get the roles.
     * 
     * @return the roles
     */
    public List<Role> getRoles() {
        return this.roles;
    }

    /**
     * Set the roles.
     * 
     * @param newRoles
     *            the roles to set
     */
    public void setRoles(final List<Role> newRoles) {
        this.roles = newRoles;
    }

    /**
     * Get the permissions.
     * 
     * @return the permissions
     */
    public List<Permission> getPermissions() {
        return this.permissions;
    }

    /**
     * Set the permissions.
     * 
     * @param newPermissions
     *            the permissions to set
     */
    public void setPermissions(final List<Permission> newPermissions) {
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

}
