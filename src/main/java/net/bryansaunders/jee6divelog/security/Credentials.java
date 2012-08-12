/**
 * 
 */
package net.bryansaunders.jee6divelog.security;/*
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

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement
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
