/**
 * 
 */
package net.bryansaunders.jee6divelog.service;

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

import net.bryansaunders.jee6divelog.dao.user.UserDao;
import net.bryansaunders.jee6divelog.model.User;

/**
 * User Service for Working with User.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Stateless
public class UserService {

    /**
     * User DAO.
     */
    @Inject
    private UserDao userDao;
    
    /**
     * EJB Context for TRansaction Rollback.
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
    public User createUser(final User user) {
        User savedUser = null;

        try {
            savedUser = this.userDao.save(user);
        } catch (ConstraintViolationException e) {
            this.context.setRollbackOnly();
        }

        return savedUser;
    }

    /**
     * Find a User by their Username. NoResultException thrown if the user is not found.
     * 
     * @param username
     *            Username to search for
     * @return User if found
     * @throws NoResultException
     *             Thrown if the entity is not found
     */
    public User findByUsername(final String username) throws NoResultException {
        final EntityManager entityManager = this.userDao.getEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        final ParameterExpression<String> usernameParam = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), usernameParam));

        final TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(usernameParam, username);

        return query.getSingleResult();
    }

}
