/**
 * 
 */
package net.bryansaunders.jee6divelog.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import net.bryansaunders.jee6divelog.DefaultDeployment;
import net.bryansaunders.jee6divelog.model.User;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class UserDaoIT {

    /**
     * Generic DAO for Testing.
     */
    @Inject
    private UserDao userDao;

    /**
     * Entity Manager for Testing.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * User Transaction.
     */
    @Inject
    private UserTransaction userTransation;

    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static WebArchive createDeployment() {
        return DefaultDeployment.getDefaultDeployment();
    }

    /**
     * Setup test.
     * 
     * @throws Exception
     *             thrown on error
     */
    @Before
    public void setup() throws Exception {
        this.userTransation.begin();
        this.entityManager.joinTransaction();
    }

    /**
     * Tear down test.
     * 
     * @throws Exception
     *             thrown on error
     */
    @After
    public void teardown() throws Exception {
        this.userTransation.rollback();
    }

    /**
     * Test method for save().
     */
    @Test
    public void ifNotNullThenSave() {
        // given
        final String email = "test@test.com";
        final String pass = "pass12314";
        
        final User validUser = new User();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail(email);
        validUser.setPassword(pass);

        // when
        final User savedUser = this.userDao.save(validUser);

        // then
        assertTrue(this.entityManager.contains(validUser));
        assertTrue(this.entityManager.contains(savedUser));
        assertEquals(savedUser, validUser);

        assertNotNull(validUser.getId());
        assertNotNull(savedUser.getId());
        assertTrue(savedUser.getId() > 0);

        // check the password
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        matcher.setHashIterations(UserDao.HASH_ITERATIONS);
        matcher.setStoredCredentialsHexEncoded(false);
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        info.setCredentials(savedUser.getPassword());
        info.setCredentialsSalt(ByteSource.Util.bytes(savedUser.getSalt()));
        
        assertTrue(matcher.doCredentialsMatch(new UsernamePasswordToken(email, pass), info));
    }

}
