package net.bryansaunders.jee6divelog.service.rest;/*
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

import javax.ws.rs.core.Response;

import net.bryansaunders.jee6divelog.DeploymentFactory;
import net.bryansaunders.jee6divelog.security.Credentials;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;

import com.jayway.restassured.http.ContentType;

/**
 * Base Test class for REST API Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Ignore
public abstract class RestApiTest {
    
    /**
     * Token Header Name.
     */
    protected static final String DL_TOKEN = "dl-token";

    /**
     * Username Header Name.
     */
    protected static final String DL_USERNAME = "dl-username";

    /**
     * Not Found Status Code.
     */
    protected static final int NOT_FOUND = Response.Status.NOT_FOUND.getStatusCode();

    /**
     * Ok Status Code.
     */
    protected static final int OK = Response.Status.OK.getStatusCode();

    /**
     * Created Status Code.
     */
    protected static final int CREATED = Response.Status.CREATED.getStatusCode();

    /**
     * Created Status Code.
     */
    protected static final int BAD_REQUEST = Response.Status.BAD_REQUEST.getStatusCode();

    /**
     * Conflict Status Code.
     */
    protected static final int CONFLICT = Response.Status.CONFLICT.getStatusCode();

    /**
     * Accepted Status Code.
     */
    protected static final int ACCEPTED = Response.Status.ACCEPTED.getStatusCode();

    /**
     * Unauthorized Status Code.
     */
    protected static final int UNAUTHORIZED = Response.Status.UNAUTHORIZED.getStatusCode();

    /**
     * Root URL.
     */
    protected static final String URL_ROOT = "http://localhost:8080/jee6divelog_test_restapi/REST";

    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static WebArchive createDeployment() {
        return DeploymentFactory.getRestApiDeployment();
    }

    /**
     * Performs a Login Action and returns the Authorization Token.
     * 
     * @param userName
     *            User Name
     * @param password
     *            User Password
     * @return Authorization Token
     */
    protected String doLogin(final String userName, final String password) {
        final Credentials credentials = new Credentials();
        credentials.setUsername(userName);
        credentials.setPassword(password);

        final String token = given().contentType(ContentType.JSON).body(credentials).expect()
                .statusCode(RestApiTest.ACCEPTED).when().post(RestApiTest.URL_ROOT + "/security/login/").asString();
        
        return token;
    }

}
