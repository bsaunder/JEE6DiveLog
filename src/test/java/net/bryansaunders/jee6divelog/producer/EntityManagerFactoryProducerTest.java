package net.bryansaunders.jee6divelog.producer;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;

import net.bryansaunders.jee6divelog.producer.EntityManagerFactoryProducer;

import org.junit.Test;

/**
 * Tests EntityManagerFactory Producer.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class EntityManagerFactoryProducerTest {

    /**
     * Tests Create Method.
     */
    @Test
    public void testCreate() {
        final EntityManagerFactoryProducer emfProducer = new EntityManagerFactoryProducer();
        
        final EntityManagerFactory emf = emfProducer.create();
        assertNotNull(emf);
    }

    /**
     * Tests Destroy Method.
     */
    @Test
    public void testDestroy() {
        final EntityManagerFactoryProducer emfProducer = new EntityManagerFactoryProducer();
        
        final EntityManagerFactory emf = emfProducer.create();
        assertNotNull(emf);
        
        emfProducer.destroy(emf);
        assertFalse(emf.isOpen());
    }

}
