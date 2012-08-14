/**
 * 
 */
package net.bryansaunders.jee6divelog.service.rest;

/*
 * #%L
 * BSNet-DiveLog
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 Bryan Saunders
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.enumerator.Permission;
import net.bryansaunders.jee6divelog.security.enumerator.Role;

import org.apache.commons.httpclient.HttpStatus;
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

        given().contentType(ContentType.JSON).body(newUser).expect().statusCode(HttpStatus.SC_CREATED).when()
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

        given().contentType(ContentType.JSON).body(newUser).expect().statusCode(HttpStatus.SC_BAD_REQUEST).when()
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
        // given
        final String requestUrl = RestApiTest.URL_ROOT + "/user/find/";
        final UserAccount loggedInUser = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);
        final String privateApiKey = loggedInUser.getPrivateApiKey();
        final String publicApiKey = loggedInUser.getPublicApiKey();

        final Map<String, String> headers = this.generateLoginHeaders(HttpMethod.POST, requestUrl, null, privateApiKey,
                publicApiKey);

        final UserAccount userAccount = new UserAccount();
        userAccount.setEmail(UserAccountApiIT.VALID_EMAIL);

        // when
        final String json = given().headers(headers).contentType(ContentType.JSON).body(userAccount).expect()
                .statusCode(HttpStatus.SC_OK).when().post(requestUrl).asString();

        // then
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
        // given
        final String requestUrl = RestApiTest.URL_ROOT + "/user/find/";
        final UserAccount loggedInUser = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);
        final String privateApiKey = loggedInUser.getPrivateApiKey();
        final String publicApiKey = loggedInUser.getPublicApiKey();

        final Map<String, String> headers = this.generateLoginHeaders(HttpMethod.POST, requestUrl, null, privateApiKey,
                publicApiKey);

        final UserAccount userAccount = new UserAccount();
        userAccount.setState("SC");
        userAccount.setLastName("Saunders");

        // when
        final String json = given().headers(headers).contentType(ContentType.JSON).body(userAccount).expect()
                .statusCode(HttpStatus.SC_OK).when().post(requestUrl).asString();

        // then
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
        // given
        final String requestUrl = RestApiTest.URL_ROOT + "/user/find/";
        final UserAccount loggedInUser = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);
        final String privateApiKey = loggedInUser.getPrivateApiKey();
        final String publicApiKey = loggedInUser.getPublicApiKey();

        final Map<String, String> headers = this.generateLoginHeaders(HttpMethod.POST, requestUrl, null, privateApiKey,
                publicApiKey);

        final UserAccount userAccount = new UserAccount();
        userAccount.setEmail("sdf@sdf.com");

        // when
        given().headers(headers).contentType(ContentType.JSON).body(userAccount).expect()
                .statusCode(HttpStatus.SC_BAD_REQUEST).when().post(requestUrl);
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
        // given
        final String requestUrl = RestApiTest.URL_ROOT + "/user/find/";
        final UserAccount loggedInUser = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);
        final String privateApiKey = loggedInUser.getPrivateApiKey();
        final String publicApiKey = loggedInUser.getPublicApiKey();

        final Map<String, String> headers = this.generateLoginHeaders(HttpMethod.POST, requestUrl, null, privateApiKey,
                publicApiKey);

        // when
        final String json = given().headers(headers).contentType(ContentType.JSON).body(new UserAccount()).expect()
                .statusCode(HttpStatus.SC_OK).when().post(requestUrl).asString();

        // then
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

    /**
     * Test Get all with Multiple Users.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    @UsingDataSet("ThreeUserAccounts-Admin.yml")
    public void ifUsersExistThenGetAll() throws Exception {
        // given
        final String requestUrl = RestApiTest.URL_ROOT + "/user";
        final UserAccount loggedInUser = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);
        final String privateApiKey = loggedInUser.getPrivateApiKey();
        final String publicApiKey = loggedInUser.getPublicApiKey();

        final Map<String, String> headers = this.generateLoginHeaders(HttpMethod.GET, requestUrl, null, privateApiKey,
                publicApiKey);

        // when
        final String json = given().headers(headers).expect().statusCode(HttpStatus.SC_OK).when().get(requestUrl)
                .asString();

        // then
        final ObjectMapper objMapper = new ObjectMapper();
        final List<UserAccount> list = objMapper.readValue(json, new TypeReference<List<UserAccount>>() {
        });
        assertNotNull(list);
        assertEquals(3, list.size());

        final UserAccount foundUser = list.get(0);
        assertNotNull(foundUser);
        assertEquals(UserAccountApiIT.VALID_EMAIL, foundUser.getEmail());
    }

    /**
     * Test Get all with a Single User.
     * 
     * @throws Exception
     *             Thrown on error
     */
    @Test
    @UsingDataSet("OneUserAccount-Admin.yml")
    public void ifOneUserThenGetOne() throws Exception {
        // given
        final String requestUrl = RestApiTest.URL_ROOT + "/user";
        final UserAccount loggedInUser = this.doLogin(UserAccountApiIT.VALID_EMAIL, UserAccountApiIT.VALID_PASSWORD);
        final String privateApiKey = loggedInUser.getPrivateApiKey();
        final String publicApiKey = loggedInUser.getPublicApiKey();

        final Map<String, String> headers = this.generateLoginHeaders(HttpMethod.GET, requestUrl, null, privateApiKey,
                publicApiKey);

        // when
        final String json = given().headers(headers).expect().statusCode(HttpStatus.SC_OK).when().get(requestUrl)
                .asString();

        // then
        final ObjectMapper objMapper = new ObjectMapper();
        final List<UserAccount> list = objMapper.readValue(json, new TypeReference<List<UserAccount>>() {
        });
        assertNotNull(list);
        assertEquals(1, list.size());

        final UserAccount foundUser = list.get(0);
        assertNotNull(foundUser);
        assertEquals(UserAccountApiIT.VALID_EMAIL, foundUser.getEmail());
    }

}
