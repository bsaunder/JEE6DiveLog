/**
 * 
 */
package net.bryansaunders.jee6divelog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import net.bryansaunders.jee6divelog.dao.user.UserDao;
import net.bryansaunders.jee6divelog.model.User;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class GenericDaoTest {

    /**
     * Generic DAO for Testing.
     */
    @Inject
    private UserDao userDao;

    /**
     * Valid User.
     */
    private User validUser1;

    /**
     * Valid user 2.
     */
    private User validUser2;

    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap
                .create(JavaArchive.class)
                .addPackages(true, "net.bryansaunders.jee6divelog")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /**
     * Sets up the Tests.
     */
    @Before
    public void setupTest() {
        this.validUser1 = new User();
        this.validUser1.setFirstName("Bryan");
        this.validUser1.setLastName("Saunders");
        this.validUser1.setEmail("btsaunde@gmail.com");
        this.validUser1.setPassword("pass123");

        this.validUser2 = new User();
        this.validUser2.setFirstName("John");
        this.validUser2.setLastName("Doe");
        this.validUser2.setEmail("jdoe@gmail.com");
        this.validUser2.setPassword("jdoe123");

        this.userDao.beginTransaction();
    }

    /**
     * Tear down the Test.
     */
    @After
    public void teardownTest() {
        this.userDao.rollbackTransaction();
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
        final User savedUser = this.userDao.save(this.validUser1);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertTrue(savedUser.getId() > 0);
    }

    /**
     * Test method for get(java.lang.Integer)}.
     */
    @Test
    public void ifFoundThenGet() {
        final User savedUser = this.userDao.save(this.validUser1);

        assertNotNull(savedUser);
        final Integer savedId = savedUser.getId();

        assertNotNull(savedId);
        assertTrue(savedId > 0);

        final User retrievedUser = this.userDao.get(savedId);
        assertEquals(savedId, retrievedUser.getId());

        assertEquals(savedUser, retrievedUser);
    }

    /**
     * Test method for get(java.lang.Integer)}.
     */
    @Test(expected = EntityNotFoundException.class)
    public void ifNotFoundThenException() {
        this.userDao.get(999);
    }

    /**
     * Test method for updates.
     */
    @Test
    public void ifFoundThenUpdate() {
        final String city = "Charleston";

        // Save
        final User savedUser = this.userDao.save(this.validUser1);

        final Integer savedId = savedUser.getId();

        // Edit
        savedUser.setCity(city);

        // Update
        final User newSavedUser = this.userDao.save(savedUser);
        assertNotNull(newSavedUser);
        assertEquals(savedId, newSavedUser.getId());
        assertEquals(city, newSavedUser.getCity());
        assertEquals(savedUser, newSavedUser);

        // Get
        final User retrievedUser = this.userDao.get(savedId);
        assertEquals(savedId, retrievedUser.getId());
        assertEquals(city, retrievedUser.getCity());
        assertEquals(newSavedUser, retrievedUser);
    }

    /**
     * Test method for save(T[]).
     */
    @Test
    public void ifArrayFullThenSave() {
        final User[] users = { this.validUser1, this.validUser2 };

        final List<User> savedUsers = this.userDao.save(users);

        assertEquals(2, savedUsers.size());
        assertTrue(savedUsers.contains(this.validUser1));
        assertTrue(savedUsers.contains(this.validUser2));

        for (final User user : savedUsers) {
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
        final User[] users = {};
        this.userDao.save(users);
    }

    /**
     * Test method for save(T[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifArrayNullThenFailOnSave() {
        final User[] users = null;
        this.userDao.save(users);
    }

    /**
     * Test method for get(java.lang.Integer[])}.
     */
    @Test
    public void ifArrayFullThenGet() {
        final User[] users = { this.validUser1, this.validUser2 };

        final List<User> savedUsers = this.userDao.save(users);
        final Integer[] integerList = { savedUsers.get(0).getId(), savedUsers.get(1).getId() };

        final List<User> retrievedUsers = this.userDao.get(integerList);

        assertEquals(2, retrievedUsers.size());
        assertTrue(retrievedUsers.contains(this.validUser1));
        assertTrue(retrievedUsers.contains(this.validUser2));
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
        final User[] users = { this.validUser1, this.validUser2 };
        this.userDao.save(users);

        final List<User> retrievedUsers = this.userDao.getAll();

        assertEquals(2, retrievedUsers.size());
        assertTrue(retrievedUsers.contains(this.validUser1));
        assertTrue(retrievedUsers.contains(this.validUser2));
    }

    /**
     * Test method for getAll().
     */
    @Test
    public void ifTableEmptyThenGetNothing() {
        final List<User> retrievedUsers = this.userDao.getAll();
        assertNotNull(retrievedUsers);
        assertEquals(0, retrievedUsers.size());
    }

    /**
     * Test method for delete(java.lang.Integer).
     */
    @Test(expected = EntityNotFoundException.class)
    public void ifFoundThenDelete() {
        final User savedUser = this.userDao.save(this.validUser1);
        final Integer savedId = savedUser.getId();

        this.userDao.delete(savedId);

        this.userDao.get(savedId);
    }

    /**
     * Test method for delete(java.lang.Integer).
     */
    @Test(expected = EntityNotFoundException.class)
    public void ifNotFoundForDeleteThenException() {
        this.userDao.delete(999);
    }

    /**
     * Test method for delete(java.lang.Integer[]).
     */
    @Test(expected = EntityNotFoundException.class)
    public void testDeleteIntegerArray() {
        final User[] users = { this.validUser1, this.validUser2 };

        final List<User> savedUsers = this.userDao.save(users);
        final Integer[] integerList = { savedUsers.get(0).getId(), savedUsers.get(1).getId() };

        this.userDao.delete(integerList);

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
    @Test(expected = EntityNotFoundException.class)
    public void testDeleteT() {
        final User savedUser = this.userDao.save(this.validUser1);
        final Integer savedId = savedUser.getId();

        this.userDao.delete(savedUser);

        this.userDao.get(savedId);

    }

    /**
     * Test method for delete(T[]).
     */
    @Test(expected = EntityNotFoundException.class)
    public void testDeleteTArray() {
        final User[] users = { this.validUser1, this.validUser2 };

        final List<User> savedUsers = this.userDao.save(users);
        final Integer[] integerList = { savedUsers.get(0).getId(), savedUsers.get(1).getId() };

        this.userDao.delete(savedUsers.get(0), savedUsers.get(1));

        this.userDao.get(integerList);
    }

    /**
     * Test method for delete(T[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifEntityArrayEmptyThenFailOnDelete() {
        final User[] integerList = {};
        this.userDao.delete(integerList);
    }

    /**
     * Test method for delete(T[]).
     */
    @Test(expected = IllegalArgumentException.class)
    public void ifEntityArrayNullThenFailOnDelete() {
        final User[] integerList = null;
        this.userDao.delete(integerList);
    }

    /**
     * Test method for deleteAll().
     */
    @Test
    public void ifTableNotEmptyThenDeleteAll() {
        final User[] users = { this.validUser1, this.validUser2 };

        this.userDao.save(users);

        this.userDao.getAll();

        this.userDao.deleteAll();

        final List<User> retrievedUsers = this.userDao.getAll();
        assertNotNull(retrievedUsers);
        assertEquals(0, retrievedUsers.size());
    }

}
