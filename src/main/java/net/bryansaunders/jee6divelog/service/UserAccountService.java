/**
 * 
 */
package net.bryansaunders.jee6divelog.service;

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

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import net.bryansaunders.jee6divelog.dao.user.UserAccountDao;
import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;
import net.bryansaunders.jee6divelog.security.enumerator.Role;

import org.jboss.logging.Logger;

/**
 * User Service for Working with User.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Stateless
public class UserAccountService {

    /**
     * Logger.
     */
    private final Logger logger = Logger.getLogger(UserAccountService.class);

    /**
     * User DAO.
     */
    @Inject
    private UserAccountDao userDao;

    /**
     * EJB Context for Transaction Rollback.
     */
    @Resource
    private EJBContext context;

    /**
     * Creates a new User.
     * 
     * @param user
     *            User to Create.
     * @return Saved user object, or null if an error occured.
     */
    public UserAccount createUser(final UserAccount user) {
        UserAccount savedUser = null;

        try {
            // Clear out Base Fields to ensure we save a new entity.
            user.setId(null);
            user.setVersion(null);
            user.setCreated(null);
            user.setUpdated(null);

            // Set Default Permissions
            final Set<Permission> defaultPermissions = Role.USER.getPermissions();
            user.setPermissions(defaultPermissions);

            savedUser = this.userDao.save(user);
        } catch (final ConstraintViolationException e) {
            this.context.setRollbackOnly();
            throw e;
        }

        return savedUser;
    }

    /**
     * Find a User by their Username. NoResultException thrown if the user is not found.
     * 
     * @param username
     *            Username to search for
     * @return User if found, null if not
     */
    public UserAccount findByUserEmail(final String username) {
        final UserAccount example = new UserAccount();
        example.setEmail(username);

        List<UserAccount> foundAccounts = null;
        UserAccount foundAccount = null;
        try {
            foundAccounts = this.userDao.findByExample(example);
            if (foundAccounts.size() == 1) {
                // We want the First Result, Should only be one anyways.
                foundAccount = foundAccounts.get(0);
            }
        } catch (final NoResultException e) {
            this.logger.info("Could not Find UserAccount for Username: " + username);
        }

        return foundAccount;
    }

    /**
     * Saves the Given User.
     * 
     * @param userAccount
     *            User to save.
     * @return Saved User
     */
    public UserAccount saveUser(final UserAccount userAccount) {
        return this.userDao.save(userAccount);
    }

    /**
     * Reset the API Key for the specified user.
     * 
     * @param username
     *            User to reset API Key for
     * @return true if the ApiKey was Reset, false otherwise
     */
    public boolean clearApiKey(final String username) {
        boolean result = false;
        final UserAccount user = this.findByUserEmail(username);
        if (user != null) {
            user.setPublicApiKey(null);
            user.setPrivateApiKey(null);
            user.setApiKeyExpiration(new Date(System.currentTimeMillis() - 5));
            this.saveUser(user);

            result = true;
        }

        return result;
    }

    /**
     * Finds all the Users that Match the provided Example.
     * 
     * @param example
     *            Example User
     * @return List of Matches
     */
    public List<UserAccount> findByExample(final UserAccount example) {
        return this.userDao.findByExample(example);
    }

    /**
     * Gets all the Users.
     * 
     * @return List of Users.
     */
    public List<UserAccount> getAll() {
        return this.userDao.getAll();
    }

}
