/**
 * 
 */
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


import java.lang.reflect.InvocationTargetException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.service.UserAccountService;
import net.bryansaunders.jee6divelog.validation.annotation.FieldMatch;
import net.bryansaunders.jee6divelog.validation.annotation.Password;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Backing Bean for Registration Page.
 * 
 * Name: regBean, Scope: Request
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Named("regBean")
@RequestScoped
@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmationPassword", message = "{regBean.fieldMatch.password}")
})
public class RegistrationBean {

    /**
     * Success String.
     */
    public static final String SUCCESS = "/register_success";

    /**
     * Failure String.
     */
    public static final String FAILURE = "/register";

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(RegistrationBean.class);

    /**
     * User Service.
     */
    @Inject
    private UserAccountService userService;

    /**
     * First Name.
     */
    @NotBlank(message = "{regBean.firstName.notBlank}")
    private String firstName;

    /**
     * Last Name.
     */
    @NotBlank(message = "{regBean.lastName.notBlank}")
    private String lastName;

    /**
     * Country.
     */
    private String country;

    /**
     * City.
     */
    private String city;

    /**
     * State.
     */
    private String state;

    /**
     * Email.
     */
    @NotBlank(message = "{regBean.email.notBlank}")
    @Email(message = "{regBean.email.email}")
    private String email;

    /**
     * Password.
     */
    @NotBlank(message = "{regBean.password.notBlank}")
    @Password(message = "{regBean.password.password}")
    private String password;

    /**
     * Password Confirmation.
     */
    @NotBlank(message = "{regBean.confirmationPassword.notBlank}")
    private String confirmationPassword;

    /**
     * Default Constructor.
     */
    public RegistrationBean() {
        this.logger.debug("RegistrationBean Created");
    }

    /**
     * Submits the Registration.
     * 
     * @return Registration Status
     */
    public String submitRegistration() {
        String registrationResult = RegistrationBean.SUCCESS;

        final UserAccount user = this.createUser();
        final UserAccount savedUser = this.userService.createUser(user);

        if (savedUser == null) {
            registrationResult = RegistrationBean.FAILURE;
        }

        return registrationResult;
    }

    /**
     * Creates a User Object from this RegistrationBean.
     * 
     * @return new user object
     */
    protected UserAccount createUser() {
        final UserAccount user = new UserAccount();

        try {
            BeanUtils.copyProperties(user, this);
        } catch (final IllegalAccessException e) {
            this.logger.error("Could Not AccessProperty while Converting RegistrationBean to User.", e);
        } catch (final InvocationTargetException e) {
            this.logger.error("Error Invoking Method while Converting RegistrationBean to User.", e);
        }

        return user;
    }

    /**
     * Gets the First Name.
     * 
     * @return the firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the First Name.
     * 
     * @param newFirstName
     *            the firstName to set
     */
    public void setFirstName(final String newFirstName) {
        this.firstName = newFirstName;
    }

    /**
     * Gets the Last Name.
     * 
     * @return the lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the Last Name.
     * 
     * @param newLastName
     *            the lastName to set
     */
    public void setLastName(final String newLastName) {
        this.lastName = newLastName;
    }

    /**
     * Gets the Country.
     * 
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the Country.
     * 
     * @param newCountry
     *            the country to set
     */
    public void setCountry(final String newCountry) {
        this.country = newCountry;
    }

    /**
     * Gets the City.
     * 
     * @return the city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets the City.
     * 
     * @param newCity
     *            the city to set
     */
    public void setCity(final String newCity) {
        this.city = newCity;
    }

    /**
     * Gets the State.
     * 
     * @return the state
     */
    public String getState() {
        return this.state;
    }

    /**
     * Sets the State.
     * 
     * @param newState
     *            the state to set
     */
    public void setState(final String newState) {
        this.state = newState;
    }

    /**
     * Gets the Email.
     * 
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the Email.
     * 
     * @param newEmail
     *            the email to set
     */
    public void setEmail(final String newEmail) {
        this.email = newEmail;
    }

    /**
     * Gets the Password.
     * 
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the Password.
     * 
     * @param newPassword
     *            the password to set
     */
    public void setPassword(final String newPassword) {
        this.password = newPassword;
    }

    /**
     * Gets the Confirmation Password.
     * 
     * @return the confirmationPassword
     */
    public String getConfirmationPassword() {
        return this.confirmationPassword;
    }

    /**
     * Sets the Password.
     * 
     * @param newConfirmationPassword
     *            the confirmationPassword to set
     */
    public void setConfirmationPassword(final String newConfirmationPassword) {
        this.confirmationPassword = newConfirmationPassword;
    }

    /**
     * Set the userService.
     * 
     * @param newUserService
     *            the userService to set
     */
    public void setUserService(final UserAccountService newUserService) {
        this.userService = newUserService;
    }

}
