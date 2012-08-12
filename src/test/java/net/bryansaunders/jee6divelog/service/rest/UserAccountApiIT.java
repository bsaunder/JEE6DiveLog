/**
 * 
 */
package net.bryansaunders.jee6divelog.service.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;
import net.bryansaunders.jee6divelog.security.enumerator.Role;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
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
     * 
     * @throws Exception
     *             Thrown on Error
     */
    @Test
    public void ifSingleCriteriaMatchesThenGetResults() throws Exception {
        final String token = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);

        final UserAccount userAccount = new UserAccount();
        userAccount.setEmail(UserAccountApiIT.VALID_EMAIL);

        final String json = given()
                .headers(UserAccountApiIT.DL_USERNAME, UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.DL_TOKEN, token)
                .contentType(ContentType.JSON).body(userAccount).expect().statusCode(RestApiTest.OK).when()
                .post(RestApiTest.URL_ROOT + "/user/find/").asString();

        assertNotNull(json);

        final ObjectMapper objMapper = new ObjectMapper();
        final List<UserAccount> list = objMapper.readValue(json, new TypeReference<List<UserAccount>>() {
        });
        assertNotNull(list);
        assertEquals(1, list.size());

        final UserAccount foundUser = list.get(0);
        assertNotNull(foundUser);
        assertEquals(UserAccountApiIT.VALID_EMAIL, foundUser.getEmail());
    }
    
    /**
     * Test find by username with Valid Username.
     * 
     * @throws Exception
     *             Thrown on Error
     */
    @Test
    @UsingDataSet("ThreeUserAccounts-Admin.yml")
    public void ifMultipleCriteriaMatchesThenGetResults() throws Exception {
        final String token = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);

        final UserAccount userAccount = new UserAccount();
        userAccount.setState("SC");
        userAccount.setLastName("Saunders");

        final String json = given()
                .headers(UserAccountApiIT.DL_USERNAME, UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.DL_TOKEN, token)
                .contentType(ContentType.JSON).body(userAccount).expect().statusCode(RestApiTest.OK).when()
                .post(RestApiTest.URL_ROOT + "/user/find/").asString();

        assertNotNull(json);

        final ObjectMapper objMapper = new ObjectMapper();
        final List<UserAccount> list = objMapper.readValue(json, new TypeReference<List<UserAccount>>() {
        });
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    /**
     * Test get by username with Invalid Username.
     */
    @Test
    public void ifCriteriaDoesntMatchThenBadRequest() {
        final String token = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);

        final UserAccount userAccount = new UserAccount();
        userAccount.setEmail("sdf@sdf.com");

        given().headers(UserAccountApiIT.DL_USERNAME, UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.DL_TOKEN, token)
                .contentType(ContentType.JSON).body(userAccount).expect().statusCode(RestApiTest.BAD_REQUEST).when()
                .post(RestApiTest.URL_ROOT + "/user/find");
    }

    /**
     * Test get by username with Blank Username.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    @UsingDataSet("ThreeUserAccounts-Admin.yml")
    public void ifCriteriaBlankThenGetAll() throws Exception {
        final String token = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);

        final String json = given()
                .headers(UserAccountApiIT.DL_USERNAME, UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.DL_TOKEN, token)
                .contentType(ContentType.JSON).body(new UserAccount()).expect().statusCode(RestApiTest.OK).when()
                .post(RestApiTest.URL_ROOT + "/user/find").asString();

        assertNotNull(json);

        final ObjectMapper objMapper = new ObjectMapper();
        final List<UserAccount> list = objMapper.readValue(json, new TypeReference<List<UserAccount>>() {
        });
        assertNotNull(list);
        assertEquals(3, list.size());

        final UserAccount foundUser = list.get(0);
        assertNotNull(foundUser);
        assertEquals(UserAccountApiIT.VALID_EMAIL, foundUser.getEmail());
    }

}
