package net.bryansaunders.jee6divelog.dao.user;

import net.bryansaunders.jee6divelog.dao.GenericDaoImpl;
import net.bryansaunders.jee6divelog.model.User;

/**
 * User DAO.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class UserDao extends GenericDaoImpl<User> {

    /**
     * Number of hash iterations.
     */
    public static final int HASH_ITERATIONS = 1024;

    /**
     * {@inheritDoc}
     */
    @Override
    public User save(final User user) {

        // Encrypt Password
        // TODO Encrypt Password

        // Save
        return super.save(user);
    }
}
