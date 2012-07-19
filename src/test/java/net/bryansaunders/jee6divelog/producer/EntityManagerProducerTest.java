/**
 * 
 */
package net.bryansaunders.jee6divelog.producer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class EntityManagerProducerTest {

    /**
     * Entity Manager Producer.
     */
    @Inject
    private EntityManagerProducer emProducer;

    /**
     * Creates Arquillian Deployment Container.
     * 
     * @return deployment container
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap
                .create(JavaArchive.class)
                .addPackages(true, "net.bryansaunders.jee6divelog")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /**
     * Tests the Producer was properly injected.
     */
    @Test
    @InSequence(1)
    public void testInjection() {
        assertNotNull(this.emProducer);
    }

    /**
     * Test create.
     */
    @Test
    public void testCreate() {
        final EntityManager em = this.emProducer.create();
        assertNotNull(em);
    }

    /**
     * Test destroy.
     */
    @Test
    public void testDestroy() {
        final EntityManager em = this.emProducer.create();
        assertNotNull(em);

        this.emProducer.destroy(em);
        assertFalse(em.isOpen());
    }

}
