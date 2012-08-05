/**
 * 
 */
package net.bryansaunders.jee6divelog.service.rest;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.LinkedList;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;
import net.bryansaunders.jee6divelog.security.enumerator.Role;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Ignore;
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
public class UserAccountApiIT extends RestApiTest {

    /**
     * Test registerUser with Valid User.
     */
    @Test
    public void ifUserValidThenRegister() {
        final UserAccount newUser = new UserAccount();
        newUser.setFirstName("Test");
        newUser.setLastName("Testerson");
        newUser.setEmail("ifUserValidThenRegister@test.com");
        newUser.setPassword("abcdef1A@");
        newUser.setRoles(new LinkedList<Role>());
        newUser.setPermissions(new LinkedList<Permission>());
        newUser.setCreated(new Date());
        newUser.setUpdated(new Date());

        given().contentType(ContentType.JSON).body(newUser).expect().statusCode(RestApiTest.CREATED).when()
                .post(RestApiTest.URL_ROOL + "/user/register/");
    }

    /**
     * Test registerUser with Invalid User.
     */
    @Test
    public void ifUserInvalidThenFailRegistration() {
        final UserAccount newUser = new UserAccount();
        newUser.setFirstName("Test");
        newUser.setRoles(new LinkedList<Role>());
        newUser.setPermissions(new LinkedList<Permission>());
        newUser.setCreated(new Date());
        newUser.setUpdated(new Date());

        given().contentType(ContentType.JSON).body(newUser).expect().statusCode(RestApiTest.BAD_REQUEST).when()
                .post(RestApiTest.URL_ROOL + "/user/register/");
    }

    /**
     * Test find by username with Valid Username.
     */
    @Ignore
    @Test
    public void ifUsernameValidThenFindUser() {
        fail("Not yet implemented");
    }

    /**
     * Test get by username with Invalid Username.
     */
    @Test
    public void ifUsernameNotFoundThenDontGetUser() {
        expect().statusCode(RestApiTest.NOT_FOUND).when().get(RestApiTest.URL_ROOL + "/user/get/sdf@sdf.com");
    }

    /**
     * Test get by username with Blank Username.
     */
    @Test
    public void ifUsernameBlankThenDontGetUser() {
        expect().statusCode(RestApiTest.NOT_FOUND).when().get(RestApiTest.URL_ROOL + "/user/get/");
    }

    /**
     * Test Identify.
     */
    @Test
    public void testIdentify() {
        fail("Not Yet Implemented");
    }

    /**
     * Test Logout.
     */
    @Test
    public void testLogout() {
        fail("Not Yet Implemented");
    }

}
