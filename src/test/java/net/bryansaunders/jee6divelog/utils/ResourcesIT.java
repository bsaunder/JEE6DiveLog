/**
 * 
 */
package net.bryansaunders.jee6divelog.utils;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import net.bryansaunders.jee6divelog.DefaultDeployment;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
@RunWith(Arquillian.class)
public class ResourcesIT {
    
    /**
     * Entity Manager.
     */
    @Inject
    private EntityManager entityManager;
    
    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static WebArchive createDeployment() {
        return DefaultDeployment.getDefaultDeployment();
    }

    /**
     * Test EntityManager Injection.
     */
    @Test
    public void testEntityManager() {
        assertNotNull(this.entityManager);
    }

}
