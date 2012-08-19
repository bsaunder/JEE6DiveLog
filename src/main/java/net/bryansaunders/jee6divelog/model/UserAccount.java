package net.bryansaunders.jee6divelog.model;/*
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


import java.util.Date;import java.util.Set;import javax.persistence.CollectionTable;import javax.persistence.Column;import javax.persistence.ElementCollection;import javax.persistence.Entity;import javax.persistence.EnumType;import javax.persistence.Enumerated;import javax.persistence.JoinColumn;import javax.persistence.Table;import javax.validation.constraints.NotNull;import javax.xml.bind.annotation.XmlRootElement;import net.bryansaunders.jee6divelog.security.enumerator.Permission;import org.hibernate.annotations.Index;import org.hibernate.annotations.LazyCollection;import org.hibernate.annotations.LazyCollectionOption;
/**
 * User Model.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Entity
@Table(name = "user")
@XmlRootElement
public class UserAccount extends DiveLogEntity {

    /**
     * User First Name.
     */
    @NotNull
    private String firstName;

    /**
     * User Last Name.
     */
    @NotNull
    private String lastName;

    /**
     * Country.
     */
    private String country;

    /**
     * State.
     */
    private String state;

    /**
     * City.
     */
    private String city;

    /**
     * User Email.
     */
    @NotNull
    @Index(name = "emailIndex")
    private String email;

    /**
     * User Password, Hashed.
     */
    @NotNull
    private String password;

    /**
     * User Public API Key.
     */
    private String publicApiKey;        /**     * User Private API Key.     */    private String privateApiKey;

    /**
     * User API Key Expiration Date.
     */
    private Date apiKeyExpiration;

    /**
     * User Permissions.
     */
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "permission")
    private Set<Permission> permissions;

    /**
     * Get the firstName.
     * 
     * @return the firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Set the firstName.
     * 
     * @param newFirstName
     *            the firstName to set
     */
    public void setFirstName(final String newFirstName) {
        this.firstName = newFirstName;
    }

    /**
     * Get the lastName.
     * 
     * @return the lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Set the lastName.
     * 
     * @param newLastName
     *            the lastName to set
     */
    public void setLastName(final String newLastName) {
        this.lastName = newLastName;
    }

    /**
     * Get the country.
     * 
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Set the country.
     * 
     * @param newCountry
     *            the country to set
     */
    public void setCountry(final String newCountry) {
        this.country = newCountry;
    }

    /**
     * Get the state.
     * 
     * @return the state
     */
    public String getState() {
        return this.state;
    }

    /**
     * Set the state.
     * 
     * @param newState
     *            the state to set
     */
    public void setState(final String newState) {
        this.state = newState;
    }

    /**
     * Get the city.
     * 
     * @return the city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Set the city.
     * 
     * @param newCity
     *            the city to set
     */
    public void setCity(final String newCity) {
        this.city = newCity;
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
    }        /**     * Get the Private apiKey.     *      * @return the apiKey     */    public String getPrivateApiKey() {        return this.privateApiKey;    }    /**     * Set the Private apiKey.     *      * @param newApiKey     *            the apiKey to set     */    public void setPrivateApiKey(final String newApiKey) {        this.privateApiKey = newApiKey;    }

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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserAccount [firstName=" + this.firstName + ", lastName=" + this.lastName + ", country=" + this.country
                + ", state=" + this.state + ", city=" + this.city + ", email=" + this.email + ", password="
                + this.password + ", apiKey=" + this.publicApiKey + ", apiKeyExpiration=" + this.apiKeyExpiration
                + ", permissions=" + this.permissions + "]";
    }

}
