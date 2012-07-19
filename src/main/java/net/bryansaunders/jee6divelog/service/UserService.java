/**
 * 
 */
package net.bryansaunders.jee6divelog.service;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import net.bryansaunders.jee6divelog.dao.user.UserDao;
import net.bryansaunders.jee6divelog.model.User;

/**
 * User Service for Working with User.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class UserService {

    /**
     * User DAO.
     */
    @Inject
    private UserDao userDao;

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
            this.userDao.beginTransaction();
            savedUser = this.userDao.save(user);
            this.userDao.commitTransaction();
        } catch (ConstraintViolationException e) {
            this.userDao.rollbackTransaction();
        }

        return savedUser;
    }

}
