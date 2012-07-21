/**
 * 
 */
package net.bryansaunders.jee6divelog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import net.bryansaunders.jee6divelog.dao.user.UserDao;
import net.bryansaunders.jee6divelog.model.User;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
     * Entity Manager for Testing.
     */
    @Inject
    private EntityManager entityManager;

    private User validUser1;

    private User validUser2;

    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "jee6divelog_test.war")
                .addPackages(true, "net.bryansaunders.jee6divelog")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
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
        final User validUser = new User();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail("btsaunde@gmail.com");
        validUser.setPassword("pass123");
        
        // when
        final User savedUser = this.userDao.save(validUser);
        
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
        final User validUser = new User();
        validUser.setFirstName("Bryan1");
        validUser.setLastName("Saunders1");
        validUser.setEmail("btsaunde@gmail.com");
        validUser.setPassword("pass123");
        
        final User savedUser = this.userDao.save(validUser);

        // when
        final Integer savedId = savedUser.getId();
        final User retrievedUser = this.userDao.get(savedId);
        
        // then
        assertNotNull(retrievedUser);
        assertTrue(this.entityManager.contains(retrievedUser));
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
        // given
        final String city = "Charleston";
        
        final User validUser = new User();
        validUser.setFirstName("Bryan2");
        validUser.setLastName("Saunders2");
        validUser.setEmail("btsaunde@gmail.com");
        validUser.setPassword("pass123");

        final User savedUser = this.userDao.save(validUser);
        final Integer savedId = savedUser.getId();

        // when
        savedUser.setCity(city);
        final User newSavedUser = this.userDao.save(savedUser);
        final User retrievedUser = this.userDao.get(savedId);
        
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
        final User validUser = new User();
        validUser.setFirstName("Bryan3");
        validUser.setLastName("Saunders3");
        validUser.setEmail("btsaunde@gmail.com");
        validUser.setPassword("pass123");
        
        final User validUser2 = new User();
        validUser2.setFirstName("Bryan4");
        validUser2.setLastName("Saunders4");
        validUser2.setEmail("btsaunde@gmail.com");
        validUser2.setPassword("pass123");
        
        final User[] users = { validUser, validUser2 };

        // when
        final List<User> savedUsers = this.userDao.save(users);
        
        // then
        assertEquals(2, savedUsers.size());
        assertTrue(savedUsers.contains(validUser));
        assertTrue(savedUsers.contains(validUser2));
        assertTrue(this.entityManager.contains(validUser));
        assertTrue(this.entityManager.contains(validUser2));

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
        // given
        final User validUser = new User();
        validUser.setFirstName("Bryan5");
        validUser.setLastName("Saunders5");
        validUser.setEmail("btsaunde@gmail.com");
        validUser.setPassword("pass123");
        
        final User validUser2 = new User();
        validUser2.setFirstName("Bryan6");
        validUser2.setLastName("Saunders6");
        validUser2.setEmail("btsaunde@gmail.com");
        validUser2.setPassword("pass123");
        
        final User[] users = { validUser, validUser2 };
        final List<User> savedUsers = this.userDao.save(users);
        final Integer[] integerList = { savedUsers.get(0).getId(), savedUsers.get(1).getId() };

        // when
        final List<User> retrievedUsers = this.userDao.get(integerList);

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
        final User validUser = new User();
        validUser.setFirstName("Bryan5");
        validUser.setLastName("Saunders5");
        validUser.setEmail("btsaunde@gmail.com");
        validUser.setPassword("pass123");
        
        final User validUser2 = new User();
        validUser2.setFirstName("Bryan6");
        validUser2.setLastName("Saunders6");
        validUser2.setEmail("btsaunde@gmail.com");
        validUser2.setPassword("pass123");
        
        final User[] users = { validUser, validUser2 };
        this.userDao.save(users);
        assertTrue(this.entityManager.contains(validUser));
        assertTrue(this.entityManager.contains(validUser2));

        // when
        final List<User> retrievedUsers = this.userDao.getAll();

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
        final List<User> retrievedUsers = this.userDao.getAll();
        assertNotNull(retrievedUsers);
        assertEquals(0, retrievedUsers.size());
    }

    /**
     * Test method for delete(java.lang.Integer).
     */
    @Ignore
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
    @Ignore
    @Test(expected = EntityNotFoundException.class)
    public void ifNotFoundForDeleteThenException() {
        this.userDao.delete(999);
    }

    /**
     * Test method for delete(java.lang.Integer[]).
     */
    @Ignore
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
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void ifIntegerArrayEmptyThenFailOnDelete() {
        final Integer[] integerList = {};
        this.userDao.delete(integerList);
    }

    /**
     * Test method for delete(java.lang.Integer[]).
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void ifIntegerArrayNullThenFailOnDelete() {
        final Integer[] integerList = null;
        this.userDao.delete(integerList);
    }

    /**
     * Test method for delete(DiveLogEntity).
     */
    @Ignore
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
    @Ignore
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
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void ifEntityArrayEmptyThenFailOnDelete() {
        final User[] integerList = {};
        this.userDao.delete(integerList);
    }

    /**
     * Test method for delete(T[]).
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void ifEntityArrayNullThenFailOnDelete() {
        final User[] integerList = null;
        this.userDao.delete(integerList);
    }

    /**
     * Test method for deleteAll().
     */
    @Ignore
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
