package net.bryansaunders.jee6divelog.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import net.bryansaunders.jee6divelog.security.enums.Permission;
import net.bryansaunders.jee6divelog.security.enums.Role;

/**
 * User Model.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Entity
@Table(name = "user")
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
    private String email;

    /**
     * User Password, Hashed.
     */
    @NotNull
    private String password;

    /**
     * User Roles.
     */
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "role")
    private List<Role> roles;

    /**
     * User Permissions.
     */
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "permission")
    private List<Permission> permissions;

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

}
