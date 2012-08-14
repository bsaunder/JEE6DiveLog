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

import java.util.Map;

import javax.ws.rs.HttpMethod;

import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.security.Credentials;

import org.apache.commons.httpclient.HttpStatus;
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
        final UserAccount loggedInUser = this.doLogin(SecurityApiIT.VALID_EMAIL, SecurityApiIT.VALID_PASSWORD);
        assertNotNull(loggedInUser);
    }

    /**
     * Test Login.
     */
    @Test
    public void ifCredentialsInvalidThenFail() {
        final Credentials credentials = new Credentials();
        credentials.setUsername("nope@fail.com");
        credentials.setPassword("wrongpass");

        given().contentType(ContentType.JSON).body(credentials).expect().statusCode(HttpStatus.SC_UNAUTHORIZED).when()
                .post(RestApiTest.URL_ROOT + "/security/login/");
    }

    /**
     * Test Identify.
     */
    @Test
    public void ifLoggedInThenIdentify() {
        // given
        final String requestUrl = RestApiTest.URL_ROOT + "/security/identify";
        final UserAccount loggedInUser = this.doLogin(SecurityApiIT.VALID_EMAIL, SecurityApiIT.VALID_PASSWORD);
        final String privateApiKey = loggedInUser.getPrivateApiKey();
        final String publicApiKey = loggedInUser.getPublicApiKey();

        final Map<String, String> headers = this.generateLoginHeaders(HttpMethod.GET, requestUrl, null, privateApiKey,
                publicApiKey);

        // when
        final UserAccount foundUser = given().headers(headers).expect().statusCode(HttpStatus.SC_OK).when()
                .get(requestUrl).as(UserAccount.class);

        // then
        assertNotNull(foundUser);
        assertEquals(SecurityApiIT.VALID_EMAIL, foundUser.getEmail());
    }

    /**
     * Test Identify.
     */
    @Test
    public void ifNotLoggedInThenIdentifyUnauthorized() {
        given().headers(RestApi.PUBLIC_KEY_HEADER, "sdfsdfs", RestApi.SIGNATURE_HEADER, "seese").expect()
                .statusCode(HttpStatus.SC_UNAUTHORIZED).when().get(RestApiTest.URL_ROOT + "/security/identify");
    }

    /**
     * Test Logout.
     */
    @Test
    public void ifNotLoggedInThenLogoutUnauthorized() {
        given().headers(RestApi.PUBLIC_KEY_HEADER, "sdfsdfs", RestApi.SIGNATURE_HEADER, "seese").expect()
                .statusCode(HttpStatus.SC_UNAUTHORIZED).when().post(RestApiTest.URL_ROOT + "/security/logout");
    }

    /**
     * Test Logout.
     */
    @Test
    public void ifLoggedInThenLogout() {
        // given
        final String requestUrl = RestApiTest.URL_ROOT + "/security/logout";
        final UserAccount loggedInUser = this.doLogin(SecurityApiIT.VALID_EMAIL, SecurityApiIT.VALID_PASSWORD);
        final String privateApiKey = loggedInUser.getPrivateApiKey();
        final String publicApiKey = loggedInUser.getPublicApiKey();

        final Map<String, String> headers = this.generateLoginHeaders(HttpMethod.POST, requestUrl, null, privateApiKey,
                publicApiKey);

        final String json = given()
                .headers(headers).expect()
                .statusCode(HttpStatus.SC_OK).when().post(requestUrl).asString();

        assertNotNull(json);
        assertEquals("true", json);
    }
}
