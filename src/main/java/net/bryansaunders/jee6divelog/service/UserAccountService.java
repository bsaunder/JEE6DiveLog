/**
 * 
 */
package net.bryansaunders.jee6divelog.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;

import net.bryansaunders.jee6divelog.dao.user.UserAccountDao;
import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;
import net.bryansaunders.jee6divelog.security.enumerator.Role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final Logger logger = LoggerFactory.getLogger(UserAccountService.class);

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

            // Set Default Roles
            final List<Role> defaultRoles = new LinkedList<Role>();
            defaultRoles.add(Role.USER);
            user.setRoles(defaultRoles);

            // Set Default Permissions
            final List<Permission> defaultPermissions = Permission.getDefaults(Role.USER);
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
        final EntityManager entityManager = this.userDao.getEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        final CriteriaQuery<UserAccount> criteriaQuery = criteriaBuilder.createQuery(UserAccount.class);
        final Root<UserAccount> root = criteriaQuery.from(UserAccount.class);
        criteriaQuery.select(root);

        final ParameterExpression<String> usernameParam = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), usernameParam));

        final TypedQuery<UserAccount> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(usernameParam, username);

        UserAccount foundAccount = null;
        try {
            foundAccount = query.getSingleResult();
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
            user.setApiKey(null);
            user.setApiKeyExpiration(new Date(System.currentTimeMillis() - 5));
            this.saveUser(user);

            result = true;
        }

        return result;
    }

}
