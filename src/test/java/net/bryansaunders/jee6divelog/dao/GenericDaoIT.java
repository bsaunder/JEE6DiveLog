/**
 * 
 */
package net.bryansaunders.jee6divelog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import net.bryansaunders.jee6divelog.DeploymentFactory;
import net.bryansaunders.jee6divelog.dao.user.UserAccountDao;
import net.bryansaunders.jee6divelog.model.UserAccount;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Integration Tests for GenericDaoImpl.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class GenericDaoIT {

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
        return DeploymentFactory.getDefaultDeployment();
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
     * Tests the Producer was properly injected.
     */
    @Test
    public void testInjection() {
        assertNotNull(this.userDao);
        assertNotNull(this.userDao.getEntityManager());
    }

    /**
     * Test method for save(DiveLogEntity).
     */
    @Test
    public void ifNotNullThenSave() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail("sdfsd@gmail.com");
        validUser.setPassword("pass234");

        // when
        final UserAccount savedUser = this.userDao.save(validUser);

        // then
        assertTrue(this.entityManager.contains(validUser));
        assertTrue(this.entityManager.contains(savedUser));
        assertEquals(savedUser, validUser);

        assertNotNull(validUser.getId());
        assertNotNull(savedUser.getId());
        assertTrue(savedUser.getId() > 0);
    }

    /**
     * Test method for get(java.lang.Integer)}.
     */
    @Test
    public void ifFoundThenGet() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan1");
        validUser.setLastName("Saunders1");
        validUser.setEmail("werwe@gmail.com");
        validUser.setPassword("pass12343");

        final UserAccount savedUser = this.userDao.save(validUser);

        // when
        final Integer savedId = savedUser.getId();
        final UserAccount retrievedUser = this.userDao.get(savedId);

        // then
        assertNotNull(retrievedUser);
        assertTrue(this.entityManager.contains(retrievedUser));
        assertEquals(savedId, retrievedUser.getId());
        assertEquals(savedUser, retrievedUser);
    }

    /**
     * Test method for get(java.lang.Integer)}.
     */
    @Test(expected = NoResultException.class)
    public void ifNotFoundThenException() {
        this.userDao.get(999);
    }

    /**
     * Test method for updates.
     */
    @Test
    public void ifFoundThenUpdate() {
        // given
        final String city = "Charleston";

        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan2");
        validUser.setLastName("Saunders2");
        validUser.setEmail("sdfsdf@gmail.com");
        validUser.setPassword("psdf123");

        final UserAccount savedUser = this.userDao.save(validUser);
        final Integer savedId = savedUser.getId();

        // when
        savedUser.setCity(city);
        final UserAccount newSavedUser = this.userDao.save(savedUser);
        final UserAccount retrievedUser = this.userDao.get(savedId);

        // then
        assertNotNull(newSavedUser);
        assertEquals(savedId, newSavedUser.getId());
        assertEquals(city, newSavedUser.getCity());
        assertEquals(savedUser, newSavedUser);

        assertEquals(savedId, retrievedUser.getId());
        assertEquals(city, retrievedUser.getCity());
        assertEquals(newSavedUser, retrievedUser);
    }

    /**
     * Test method for save(T[]).
     */
    @Test
    public void ifArrayFullThenSave() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan3");
        validUser.setLastName("Saunders3");
        validUser.setEmail("yrw@gmail.com");
        validUser.setPassword("patwt123");

        final UserAccount validUser2 = new UserAccount();
        validUser2.setFirstName("Bryan4");
        validUser2.setLastName("Saunders4");
        validUser2.setEmail("tretue@gmail.com");
        validUser2.setPassword("dfghs123");

        final UserAccount[] users = { validUser, validUser2 };

        // when
        final List<UserAccount> savedUsers = this.userDao.save(users);

        // then
        assertEquals(2, savedUsers.size());
        assertTrue(savedUsers.contains(validUser));
        assertTrue(savedUsers.contains(validUser2));
        assertTrue(this.entityManager.contains(validUser));
        assertTrue(this.entityManager.contains(validUser2));

        for (final UserAccount user : savedUsers) {
            final Integer savedId = user.getId();
            assertNotNull(savedId);
            assertTrue(savedId > 0);
        }
    }

    /**
     * Test method for save(T[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifArrayEmptyThenFailOnSave() {
        final UserAccount[] users = {};
        this.userDao.save(users);
    }

    /**
     * Test method for save(T[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifArrayNullThenFailOnSave() {
        final UserAccount[] users = null;
        this.userDao.save(users);
    }

    /**
     * Test method for get(java.lang.Integer[])}.
     */
    @Test
    public void ifArrayFullThenGet() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan5");
        validUser.setLastName("Saunders5");
        validUser.setEmail("btsaunde@gmail.com");
        validUser.setPassword("pass123");

        final UserAccount validUser2 = new UserAccount();
        validUser2.setFirstName("Bryan6");
        validUser2.setLastName("Saunders6");
        validUser2.setEmail("btsaunde@gmail.com");
        validUser2.setPassword("pass123");

        final UserAccount[] users = { validUser, validUser2 };
        final List<UserAccount> savedUsers = this.userDao.save(users);
        final Integer[] integerList = { savedUsers.get(0).getId(), savedUsers.get(1).getId() };

        // when
        final List<UserAccount> retrievedUsers = this.userDao.get(integerList);

        // then
        assertEquals(2, retrievedUsers.size());
        assertTrue(retrievedUsers.contains(validUser));
        assertTrue(retrievedUsers.contains(validUser2));
    }

    /**
     * Test method for get(java.lang.Integer[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifArrayEmptyThenFailOnGet() {
        final Integer[] integerList = {};
        this.userDao.get(integerList);
    }

    /**
     * Test method for get(java.lang.Integer[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifArrayNullThenFailOnGet() {
        final Integer[] integerList = null;
        this.userDao.get(integerList);
    }

    /**
     * Test method for getAll().
     */
    @Test
    public void ifTableNotEmptyThenGetAll() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan5");
        validUser.setLastName("Saunders5");
        validUser.setEmail("sdhers@gmail.com");
        validUser.setPassword("sdge4");

        final UserAccount validUser2 = new UserAccount();
        validUser2.setFirstName("Bryan6");
        validUser2.setLastName("Saunders6");
        validUser2.setEmail("sdfg@gmail.com");
        validUser2.setPassword("segsd");

        final UserAccount[] users = { validUser, validUser2 };
        this.userDao.save(users);
        assertTrue(this.entityManager.contains(validUser));
        assertTrue(this.entityManager.contains(validUser2));

        // when
        final List<UserAccount> retrievedUsers = this.userDao.getAll();

        // then
        assertEquals(2, retrievedUsers.size());
        assertTrue(retrievedUsers.contains(validUser));
        assertTrue(retrievedUsers.contains(validUser2));
    }

    /**
     * Test method for getAll().
     */
    @Test
    public void ifTableEmptyThenGetNothing() {
        final List<UserAccount> retrievedUsers = this.userDao.getAll();
        assertNotNull(retrievedUsers);
        assertEquals(0, retrievedUsers.size());
    }

    /**
     * Test method for delete(java.lang.Integer).
     */
    @Test(expected = NoResultException.class)
    public void ifFoundThenDelete() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan7");
        validUser.setLastName("Saunders7");
        validUser.setEmail("sdf@gmail.com");
        validUser.setPassword("pas3g4g");

        final UserAccount savedUser = this.userDao.save(validUser);
        final Integer savedId = savedUser.getId();

        // when
        this.userDao.delete(savedId);

        // then
        this.userDao.get(savedId);
    }

    /**
     * Test method for delete(java.lang.Integer).
     */
    @Test(expected = NoResultException.class)
    public void ifNotFoundForDeleteThenException() {
        this.userDao.delete(999);
    }

    /**
     * Test method for delete(java.lang.Integer[]).
     */
    @Test(expected = NoResultException.class)
    public void testDeleteIntegerArray() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan8");
        validUser.setLastName("Saunders8");
        validUser.setEmail("sfghf@gmail.com");
        validUser.setPassword("pasfghfh4");

        final UserAccount validUser2 = new UserAccount();
        validUser2.setFirstName("Bryan9");
        validUser2.setLastName("Saunders9");
        validUser2.setEmail("ghjkg@gmail.com");
        validUser2.setPassword("pdfght44g");

        UserAccount[] users = { validUser, validUser2 };
        final List<UserAccount> savedUsers = this.userDao.save(users);
        final Integer[] integerList = { savedUsers.get(0).getId(), savedUsers.get(1).getId() };

        // when
        this.userDao.delete(integerList);

        // then
        this.userDao.get(integerList);
    }

    /**
     * Test method for delete(java.lang.Integer[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifIntegerArrayEmptyThenFailOnDelete() {
        final Integer[] integerList = {};
        this.userDao.delete(integerList);
    }

    /**
     * Test method for delete(java.lang.Integer[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifIntegerArrayNullThenFailOnDelete() {
        final Integer[] integerList = null;
        this.userDao.delete(integerList);
    }

    /**
     * Test method for delete(DiveLogEntity).
     */
    @Test(expected = NoResultException.class)
    public void testDeleteT() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan14");
        validUser.setLastName("Saunders14");
        validUser.setEmail("sgkkkf@gmail.com");
        validUser.setPassword("pahhhfh4");

        final UserAccount savedUser = this.userDao.save(validUser);
        final Integer savedId = savedUser.getId();

        // when
        this.userDao.delete(savedUser);

        // then
        this.userDao.get(savedId);
    }

    /**
     * Test method for delete(T[]).
     */
    @Test(expected = NoResultException.class)
    public void testDeleteTArray() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan10");
        validUser.setLastName("Saunders10");
        validUser.setEmail("sghdf@gmail.com");
        validUser.setPassword("pas678fh4");

        final UserAccount validUser2 = new UserAccount();
        validUser2.setFirstName("Bryan11");
        validUser2.setLastName("Saunders11");
        validUser2.setEmail("gfdghfgkg@gmail.com");
        validUser2.setPassword("p345ht44g");

        UserAccount[] users = { validUser, validUser2 };
        final List<UserAccount> savedUsers = this.userDao.save(users);
        final Integer[] integerList = { savedUsers.get(0).getId(), savedUsers.get(1).getId() };

        // when
        this.userDao.delete(savedUsers.get(0), savedUsers.get(1));

        // then
        this.userDao.get(integerList);
    }

    /**
     * Test method for delete(T[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifEntityArrayEmptyThenFailOnDelete() {
        final UserAccount[] integerList = {};
        this.userDao.delete(integerList);
    }

    /**
     * Test method for delete(T[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifEntityArrayNullThenFailOnDelete() {
        final UserAccount[] integerList = null;
        this.userDao.delete(integerList);
    }

    /**
     * Test method for deleteAll().
     */
    @Test
    public void ifTableNotEmptyThenDeleteAll() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan12");
        validUser.setLastName("Saunders12");
        validUser.setEmail("sfghf@gmail.com");
        validUser.setPassword("pasfghfh4");

        final UserAccount validUser2 = new UserAccount();
        validUser2.setFirstName("Bryan13");
        validUser2.setLastName("Saunders13");
        validUser2.setEmail("g567gffg@gmail.com");
        validUser2.setPassword("pd956t44g");

        UserAccount[] users = { validUser, validUser2 };
        this.userDao.save(users);

        // when
        this.userDao.deleteAll();

        // then
        final List<UserAccount> retrievedUsers = this.userDao.getAll();
        assertNotNull(retrievedUsers);
        assertEquals(0, retrievedUsers.size());
    }

}
