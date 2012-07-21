package net.bryansaunders.jee6divelog.dao.user;

import net.bryansaunders.jee6divelog.dao.GenericDaoImpl;
import net.bryansaunders.jee6divelog.model.User;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

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
        final String password = user.getPassword();

        final RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        final String salt = rng.nextBytes().toString();
        final String hashedPasswordBase64 = new Sha256Hash(password, salt, HASH_ITERATIONS).toBase64();

        user.setSalt(salt);
        user.setPassword(hashedPasswordBase64);

        // Save
        return super.save(user);
    }

}
