package net.bryansaunders.jee6divelog.service.rest;

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
                .statusCode(RestApiTest.ACCEPTED).when().post(RestApiTest.URL_ROOT + "/user/login/").asString();
        
        return token;
    }

}
