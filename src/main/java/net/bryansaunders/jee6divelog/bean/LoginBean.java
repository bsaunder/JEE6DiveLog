package net.bryansaunders.jee6divelog.bean;/*
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


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import net.bryansaunders.jee6divelog.security.Credentials;
import net.bryansaunders.jee6divelog.security.Identity;

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
     * Login Success.
     */
    public static final String SUCCESS = "/user/account";

    /**
     * Login Failure.
     */
    public static final String FAILURE = "/login";

    /**
     * User Email.
     */
    private String email;

    /**
     * User Password.
     */
    private String password;

    /**
     * Remember Me.
     */
    private boolean rememberMe;

    /**
     * Login Error Flag.
     */
    private boolean loginError;

    /**
     * Identity.
     */
    @Inject
    private Identity identity;

    /**
     * User Entered Credentials.
     */
    @Inject
    private Credentials credentials;

    /**
     * Logs the user in.
     * 
     * @return Login Result
     */
    public String login() {
        String returnValue = LoginBean.FAILURE;

        this.credentials.setPassword(this.password);
        this.credentials.setUsername(this.email);
        this.identity.setRememberMe(this.rememberMe);

        if (this.identity.login()) {
            returnValue = LoginBean.SUCCESS;
        } else {
            final FacesContext facesContext = FacesContext.getCurrentInstance();
            final FacesMessage errorMessage = new FacesMessage("Invalid login information.");
            facesContext.addMessage("loginform:email", errorMessage);

            this.setLoginError(true);
        }

        return returnValue;
    }

    /**
     * Get the loginError.
     * 
     * @return the loginError
     */
    public boolean isLoginError() {
        return this.loginError;
    }

    /**
     * Set the loginError.
     * 
     * @param newLoginError
     *            the loginError to set
     */
    public void setLoginError(final boolean newLoginError) {
        this.loginError = newLoginError;
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
     * Set the identity.
     * 
     * @param newIdentity
     *            the identity to set
     */
    public void setIdentity(final Identity newIdentity) {
        this.identity = newIdentity;
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

}
