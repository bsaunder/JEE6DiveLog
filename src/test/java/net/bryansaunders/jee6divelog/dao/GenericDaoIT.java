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

import net.bryansaunders.jee6divelog.DeploymentFactory;
import net.bryansaunders.jee6divelog.dao.user.UserAccountDao;
import net.bryansaunders.jee6divelog.model.UserAccount;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.DataSource;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Integration Tests for GenericDaoImpl.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
@DataSource("java:jboss/datasources/ExampleDS")
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
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static WebArchive createDeployment() {
        return DeploymentFactory.getDefaultDeployment();
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
    @UsingDataSet("Empty.yml")
    @ShouldMatchDataSet(value = "expected/GenericDaoIT-ifNotNullThenSave.yml", excludeColumns = { "creation",
            "updated", "version" })
    public void ifNotNullThenSave() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail("bryan@test.com");
        validUser.setPassword("abcdef1A@");
        validUser.setCity("Charleston");
        validUser.setState("SC");
        validUser.setCountry("USA");

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
    @UsingDataSet("TwoUserAccounts.yml")
    public void ifFoundThenGet() {
        // given

        // when
        final UserAccount retrievedUser = this.userDao.get(1);

        // then
        assertNotNull(retrievedUser);
        assertEquals(new Integer(1), retrievedUser.getId());
        assertEquals("Bryan", retrievedUser.getFirstName());
        assertEquals("Saunders", retrievedUser.getLastName());
    }

    /**
     * Test method for get(java.lang.Integer)}.
     */
    @Test(expected = NoResultException.class)
    @UsingDataSet("Empty.yml")
    public void ifNotFoundThenException() {
        this.userDao.get(999);
    }

    /**
     * Test method for updates.
     */
    @Test
    @UsingDataSet("TwoUserAccounts.yml")
    @ShouldMatchDataSet(value = "expected/GenericDaoIT-ifFoundThenUpdate.yml", excludeColumns = { "creation",
            "updated", "version" })
    public void ifFoundThenUpdate() {
        // given
        final String newCity = "Myrtle Beach";
        final String newState = "South Carolina";

        // when
        final UserAccount retrievedUser = this.userDao.get(1);
        retrievedUser.setCity(newCity);
        retrievedUser.setState(newState);

        final UserAccount savedUser = this.userDao.save(retrievedUser);

        // then
        assertNotNull(savedUser);
        assertEquals(new Integer(1), savedUser.getId());
        assertEquals(newCity, savedUser.getCity());
        assertEquals(newState, savedUser.getState());
        assertEquals(savedUser, retrievedUser);
    }

    /**
     * Test method for save(T[]).
     */
    @Test
    @UsingDataSet("Empty.yml")
    @ShouldMatchDataSet(value = "expected/GenericDaoIT-ifArrayFullThenSave.yml", excludeColumns = { "id", "creation",
            "updated", "version" })
    public void ifArrayFullThenSave() {
        // given
        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail("bryan@test.com");
        validUser.setCity("Charleston");
        validUser.setState("SC");
        validUser.setCountry("USA");
        validUser.setPassword("abcdef1A@");

        final UserAccount validUser2 = new UserAccount();
        validUser2.setFirstName("Crystal");
        validUser2.setLastName("Crystalson");
        validUser2.setEmail("crystal@test.com");
        validUser2.setCity("Charleston");
        validUser2.setState("SC");
        validUser2.setCountry("USA");
        validUser2.setPassword("abcdef1A@");

        final UserAccount[] users = { validUser, validUser2 };

        // when
        final List<UserAccount> savedUsers = this.userDao.save(users);

        // then
        assertEquals(2, savedUsers.size());
        assertTrue(savedUsers.contains(validUser));
        assertTrue(savedUsers.contains(validUser2));

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
    @UsingDataSet("TwoUserAccounts.yml")
    public void ifArrayFullThenGet() {
        // given
        final Integer[] integerList = { 1, 2 };

        // when
        final List<UserAccount> retrievedUsers = this.userDao.get(integerList);

        // then
        assertEquals(2, retrievedUsers.size());
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
    @UsingDataSet("TwoUserAccounts.yml")
    public void ifTableNotEmptyThenGetAll() {
        // given

        // when
        final List<UserAccount> retrievedUsers = this.userDao.getAll();

        // then
        assertEquals(2, retrievedUsers.size());
    }

    /**
     * Test method for getAll().
     */
    @Test
    @UsingDataSet("Empty.yml")
    public void ifTableEmptyThenGetNothing() {
        final List<UserAccount> retrievedUsers = this.userDao.getAll();
        assertNotNull(retrievedUsers);
        assertEquals(0, retrievedUsers.size());
    }

    /**
     * Test method for delete(java.lang.Integer).
     */
    @Test
    @UsingDataSet("OneUserAccount.yml")
    @ShouldMatchDataSet("Empty.yml")
    public void ifFoundThenDelete() {
        // given

        // when
        this.userDao.delete(1);

        // then
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
    @Test
    @UsingDataSet("TwoUserAccounts.yml")
    @ShouldMatchDataSet("Empty.yml")
    public void ifAllFoundThenDeleteAll() {
        // given
        final Integer[] integerList = { 1, 2 };

        // when
        this.userDao.delete(integerList);

        // then
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
    @Test
    @UsingDataSet("OneUserAccount.yml")
    @ShouldMatchDataSet("Empty.yml")
    public void testDeleteT() {
        // given
        final UserAccount userAccount = this.userDao.get(1);

        // when
        this.userDao.delete(userAccount);

        // then
    }

    /**
     * Test method for delete(T[]).
     */
    @Test
    @UsingDataSet("TwoUserAccounts.yml")
    @ShouldMatchDataSet("Empty.yml")
    public void testDeleteTArray() {
        // given
        final UserAccount validUser = this.userDao.get(1);
        final UserAccount validUser2 = this.userDao.get(2);

        final UserAccount[] users = { validUser, validUser2 };

        // when
        this.userDao.delete(users);

        // then
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
    @UsingDataSet("TwoUserAccounts.yml")
    @ShouldMatchDataSet("Empty.yml")
    public void ifTableNotEmptyThenDeleteAll() {
        // given

        // when
        this.userDao.deleteAll();

        // then
    }

    /**
     * Test method for findByExample().
     */
    @Test
    @UsingDataSet("TwoUserAccounts.yml")
    public void ifExampleMatchThenFindExampleMatch() {
        // given
        final UserAccount example = new UserAccount();
        example.setFirstName("Bryan");
        example.setLastName("Saunders");

        // when
        final List<UserAccount> foundAccounts = this.userDao.findByExample(example);

        // then
        assertNotNull(foundAccounts);
        assertEquals(1, foundAccounts.size());

        final UserAccount foundAccount = foundAccounts.get(0);
        assertNotNull(foundAccount);
        assertEquals("Bryan", foundAccount.getFirstName());
        assertEquals("Saunders", foundAccount.getLastName());
    }

    /**
     * Test method for findByExample().
     */
    @Test
    @UsingDataSet("Empty.yml")
    public void ifEmptyTableThenNoExampleMatch() {
        // given
        final UserAccount example = new UserAccount();
        example.setFirstName("Mike");
        example.setLastName("Johnson");

        // when
        final List<UserAccount> foundAccounts = this.userDao.findByExample(example);

        // then
        assertNotNull(foundAccounts);
        assertEquals(0, foundAccounts.size());
    }

    /**
     * Test method for findByExample().
     */
    @Test
    @UsingDataSet("TwoUserAccounts.yml")
    public void ifNoExampleMatchThenNoExampleMatch() {
        // given
        final UserAccount example = new UserAccount();
        example.setFirstName("John");
        example.setLastName("Doe");

        // when
        final List<UserAccount> foundAccounts = this.userDao.findByExample(example);

        // then
        assertNotNull(foundAccounts);
        assertEquals(0, foundAccounts.size());
    }

}
