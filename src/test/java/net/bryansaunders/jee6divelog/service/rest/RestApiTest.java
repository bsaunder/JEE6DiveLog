package net.bryansaunders.jee6divelog.service.rest;

import javax.ws.rs.core.Response;

import net.bryansaunders.jee6divelog.DeploymentFactory;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;

/**
 * Base Test class for REST API Tests.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Ignore
public abstract class RestApiTest {

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
     * Root URL.
     */
    protected static final String URL_ROOL = "http://localhost:8080/jee6divelog_test/REST";

    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static WebArchive createDeployment() {
        return DeploymentFactory.getRestApiDeployment();
    }

}
