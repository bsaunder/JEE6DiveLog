/**
 * 
 */
package net.bryansaunders.jee6divelog.service.rest;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.LinkedHashSet;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;
import net.bryansaunders.jee6divelog.security.enumerator.Role;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.DataSource;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.http.ContentType;

/**
 * Unit Tests for User Account REST API.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
@DataSource("java:jboss/datasources/ExampleDS")
@UsingDataSet("OneUserAccount-Admin.yml")
public class UserAccountApiIT extends RestApiTest {

    /**
     * Email for Valid Admin Account.
     */
    private static final String VALID_EMAIL = "bryan@test.com";

    /**
     * Password for Valid Account.
     */
    private static final String VALID_PASSWORD = "abcdef1A@";

    /**
     * Test registerUser with Valid User.
     */
    @Test
    @UsingDataSet("Empty.yml")
    public void ifUserValidThenRegister() {
        final UserAccount newUser = new UserAccount();
        newUser.setFirstName("Test");
        newUser.setLastName("Testerson");
        newUser.setEmail("ifUserValidThenRegister@test.com");
        newUser.setPassword(VALID_PASSWORD);
        newUser.setRoles(new LinkedHashSet<Role>());
        newUser.setPermissions(new LinkedHashSet<Permission>());
        newUser.setCreated(new Date());
        newUser.setUpdated(new Date());

        given().contentType(ContentType.JSON).body(newUser).expect().statusCode(RestApiTest.CREATED).when()
                .post(RestApiTest.URL_ROOT + "/user/register/");
    }

    /**
     * Test registerUser with Invalid User.
     */
    @Test
    public void ifUserInvalidThenFailRegistration() {
        final UserAccount newUser = new UserAccount();
        newUser.setFirstName("Test");
        newUser.setRoles(new LinkedHashSet<Role>());
        newUser.setPermissions(new LinkedHashSet<Permission>());
        newUser.setCreated(new Date());
        newUser.setUpdated(new Date());

        given().contentType(ContentType.JSON).body(newUser).expect().statusCode(RestApiTest.BAD_REQUEST).when()
                .post(RestApiTest.URL_ROOT + "/user/register/");
    }

    /**
     * Test find by username with Valid Username.
     */
    @Test
    @UsingDataSet("OneUserAccount.yml")
    public void ifUsernameValidThenFindUser() {
        final String token = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);

        final UserAccount foundUser = given()
                .headers(UserAccountApiIT.DL_USERNAME, UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.DL_TOKEN, token)
                .expect().statusCode(OK).when()
                .get(RestApiTest.URL_ROOT + "/user/find?userName=" + UserAccountApiIT.VALID_EMAIL)
                .as(UserAccount.class);

        assertNotNull(foundUser);
        assertEquals(new Integer(1), foundUser.getId());
        assertEquals(UserAccountApiIT.VALID_EMAIL, foundUser.getEmail());
    }

    /**
     * Test get by username with Invalid Username.
     */
    @Test
    public void ifUsernameNotFoundThenDontGetUser() {
        expect().statusCode(RestApiTest.BAD_REQUEST).when()
                .get(RestApiTest.URL_ROOT + "/user/find?userName=sdf@sdf.com");
    }

    /**
     * Test get by username with Blank Username.
     */
    @Test
    public void ifUsernameBlankThenDontGetUser() {
        expect().statusCode(RestApiTest.BAD_REQUEST).when().get(RestApiTest.URL_ROOT + "/user/find?userName=");
    }

}
