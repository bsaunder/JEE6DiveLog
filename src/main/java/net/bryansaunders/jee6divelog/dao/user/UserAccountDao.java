package net.bryansaunders.jee6divelog.dao.user;

import net.bryansaunders.jee6divelog.dao.GenericDaoImpl;
import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.utils.HashUtils;

/**
 * User DAO.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class UserAccountDao extends GenericDaoImpl<UserAccount> {

    /**
     * Number of hash iterations.
     */
    public static final int HASH_ITERATIONS = 1024;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAccount save(final UserAccount user) {

        // Encrypt Password
        final String password = user.getPassword();
        
        final String hashedPass = HashUtils.toSha256(password);
        final String hashedEncodedPass = HashUtils.base64Encode(hashedPass);
        
        user.setPassword(hashedEncodedPass);

        // Save
        return super.save(user);
    }
}
