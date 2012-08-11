/**
 * 
 */
package net.bryansaunders.jee6divelog.dao.user;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import net.bryansaunders.jee6divelog.DeploymentFactory;
import net.bryansaunders.jee6divelog.model.UserAccount;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.DataSource;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
@DataSource("java:jboss/datasources/ExampleDS")
public class UserAccountDaoIT {

    /**
     * Generic DAO for Testing.
     */
    @Inject
    private UserAccountDao userDao;

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
     * Test method for save().
     */
    @Test
    @UsingDataSet("Empty.yml")
    @ShouldMatchDataSet("expected/GenericDaoIT-ifNotNullThenSave.yml")
    public void ifNotNullThenSave() {
        // given
        final String pass = "abcdef1A@";

        final UserAccount validUser = new UserAccount();
        validUser.setFirstName("Bryan");
        validUser.setLastName("Saunders");
        validUser.setEmail("bryan@test.com");
        validUser.setCity("Charleston");
        validUser.setState("SC");
        validUser.setCountry("USA");
        validUser.setPassword(pass);

        // when
        final UserAccount savedUser = this.userDao.save(validUser);

        // check the password
        final String hashedEncodedPass = Base64.encodeBase64String(DigestUtils.sha256Hex(pass).getBytes());
        assertEquals(hashedEncodedPass, savedUser.getPassword());
    }

    /**
     * Tests method for save(). Already existing Users should be updated.
     */
    @Test
    @UsingDataSet("OneUserAccount.yml")
    @ShouldMatchDataSet(value = "expected/UserAccountDaoIT-ifExistsThenUpdate.yml", excludeColumns = { "creation",
            "updated", "version" })
    public void ifExistsThenUpdate() {
        // given
        final UserAccount foundUser = this.userDao.get(1);
        foundUser.setVersion(2);
        foundUser.setLastName("Sanders");
        foundUser.setCity("Myrtle Beach");
        foundUser.setState("South Carolina");

        // when
        this.userDao.save(foundUser);

        // then
    }

}
