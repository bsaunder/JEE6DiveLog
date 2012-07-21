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
import net.bryansaunders.jee6divelog.model.UserAccount;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
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
public class UserAccountDaoIT {

    /**
     * Generic DAO for Testing.
     */
    @Inject
    private UserAccountDao userDao;

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

        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail(email);
        validUser.setPassword(pass);

        // when
        final UserAccount savedUser = this.userDao.save(validUser);

        // then
        assertTrue(this.entityManager.contains(validUser));
        assertTrue(this.entityManager.contains(savedUser));
        assertEquals(savedUser, validUser);

        assertNotNull(validUser.getId());
        assertNotNull(savedUser.getId());
        assertTrue(savedUser.getId() > 0);

        // check the password
        final String hashedEncodedPass = Base64.encodeBase64String(DigestUtils.sha256Hex(pass).getBytes());
        assertEquals(hashedEncodedPass, savedUser.getPassword());
    }

}
