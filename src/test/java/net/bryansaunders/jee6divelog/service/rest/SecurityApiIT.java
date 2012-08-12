/**
 * 
 */
package net.bryansaunders.jee6divelog.service.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.Credentials;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.DataSource;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.http.ContentType;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
@DataSource("java:jboss/datasources/ExampleDS")
@UsingDataSet("OneUserAccount-Admin.yml")
public class SecurityApiIT extends RestApiTest {

    /**
     * Email for Valid Admin Account.
     */
    private static final String VALID_EMAIL = "bryan@test.com";

    /**
     * Password for Valid Account.
     */
    private static final String VALID_PASSWORD = "abcdef1A@";

    /**
     * Test Login.
     */
    @Test
    public void ifCredentialsValidThenLogin() {
        final String token = this.doLogin(SecurityApiIT.VALID_EMAIL, SecurityApiIT.VALID_PASSWORD);
        assertNotNull(token);
    }

    /**
     * Test Login.
     */
    @Test
    public void ifCredentialsInvalidThenFail() {
        final Credentials credentials = new Credentials();
        credentials.setUsername("nope@fail.com");
        credentials.setPassword("wrongpass");

        given().contentType(ContentType.JSON).body(credentials).expect().statusCode(RestApiTest.UNAUTHORIZED).when()
                .post(RestApiTest.URL_ROOT + "/security/login/");
    }

    /**
     * Test Identify.
     */
    @Test
    public void ifLoggedInThenIdentify() {
        final String token = this.doLogin(SecurityApiIT.VALID_EMAIL, SecurityApiIT.VALID_PASSWORD);

        final UserAccount foundUser = given()
                .headers(SecurityApiIT.DL_USERNAME, SecurityApiIT.VALID_EMAIL, SecurityApiIT.DL_TOKEN, token)
                .expect().statusCode(OK).when().get(RestApiTest.URL_ROOT + "/security/identify").as(UserAccount.class);

        assertNotNull(foundUser);
        assertEquals(SecurityApiIT.VALID_EMAIL, foundUser.getEmail());
    }

    /**
     * Test Identify.
     */
    @Test
    public void ifNotLoggedInThenIdentifyUnauthorized() {
        given().headers(SecurityApiIT.DL_USERNAME, SecurityApiIT.VALID_EMAIL, SecurityApiIT.DL_TOKEN, "1233")
                .expect().statusCode(RestApiTest.UNAUTHORIZED).when().get(RestApiTest.URL_ROOT + "/security/identify");
    }

    /**
     * Test Logout.
     */
    @Test
    public void ifNotLoggedInThenLogoutUnauthorized() {
        given().headers(SecurityApiIT.DL_USERNAME, SecurityApiIT.VALID_EMAIL, SecurityApiIT.DL_TOKEN, "1233")
                .expect().statusCode(RestApiTest.UNAUTHORIZED).when().post(RestApiTest.URL_ROOT + "/security/logout");
        // String json = get(RestApiTest.URL_ROOT + "/security/logout").asString();
        // System.out.println("JSON: " + json);
    }

    /**
     * Test Logout.
     */
    @Test
    public void ifLoggedInThenLogout() {
        final String token = this.doLogin(SecurityApiIT.VALID_EMAIL, SecurityApiIT.VALID_PASSWORD);

        final String json = given()
                .headers(SecurityApiIT.DL_USERNAME, SecurityApiIT.VALID_EMAIL, SecurityApiIT.DL_TOKEN, token)
                .expect().statusCode(OK).when().post(RestApiTest.URL_ROOT + "/security/logout").asString();

        assertNotNull(json);
        assertEquals("true", json);
    }
}
