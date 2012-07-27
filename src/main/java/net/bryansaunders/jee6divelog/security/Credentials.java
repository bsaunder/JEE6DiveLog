/**
 * 
 */
package net.bryansaunders.jee6divelog.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User Entered Credentials.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@SessionScoped
@Named("credentials")
public class Credentials implements Serializable {

    /**
     * Serial Version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Credentials.class);

    /**
     * Username.
     */
    private String username;

    /**
     * Password.
     */
    private String password;

    /**
     * Default Constructor.
     */
    public Credentials() {
        this.username = "";
        this.password = "";
    }

    /**
     * Get the username.
     * 
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Set the username.
     * 
     * @param newUsername
     *            the username to set
     */
    public void setUsername(final String newUsername) {
        this.logger.debug("Setting Username Credential: " + newUsername);
        this.username = newUsername;
    }

    /**
     * Get the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Set the password.
     * 
     * @param newPassword
     *            the password to set
     */
    public void setPassword(final String newPassword) {
        this.logger.debug("Setting Username Password: " + newPassword);
        this.password = newPassword;
    }

    @Override
    public String toString() {
        return "Credentials[username: " + this.username + ", password: " + this.password + "]";
    }
}
