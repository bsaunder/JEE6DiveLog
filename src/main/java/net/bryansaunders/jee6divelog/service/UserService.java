/**
 * 
 */
package net.bryansaunders.jee6divelog.service;

import javax.ejb.Stateless;
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
@Stateless
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
            savedUser = this.userDao.save(user);
        } catch (ConstraintViolationException e) {
            //TODO Roll Back Transaction
        }

        return savedUser;
    }

}
