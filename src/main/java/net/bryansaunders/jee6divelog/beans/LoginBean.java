package net.bryansaunders.jee6divelog.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Backing Bean for Login Page.
 * 
 * Name: loginBean, Scope: Request
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Named("loginBean")
@RequestScoped
public class LoginBean {

    /**
     * Success String.
     */
    public static final String SUCCESS = "account/index";

    /**
     * Failure String.
     */
    public static final String FAILURE = "login";

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginBean.class);

    /**
     * Email.
     */
    private String email;

    /**
     * Password.
     */
    private String password;

    /**
     * Remember Me.
     */
    private boolean rememberMe;

    /**
     * Logs the user in.
     * 
     * @return Login result
     */
    public String login() {
        String returnValue = LoginBean.FAILURE;

        // TODO Implement Login

        return returnValue;
    }

    /**
     * Get the email.
     * 
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the email.
     * 
     * @param newEmail
     *            the email to set
     */
    public void setEmail(final String newEmail) {
        this.email = newEmail;
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
        this.password = newPassword;
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
    public void setRememberMe(boolean newRememberMe) {
        this.rememberMe = newRememberMe;
    }

}
