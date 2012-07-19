/**
 * 
 */
package net.bryansaunders.jee6divelog.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Producer for EntityManagerFactory.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class EntityManagerFactoryProducer {

    /**
     * Creates an Entity Manager Factory.
     * 
     * @return EntityManagerFactory
     */
    @Produces
    @ApplicationScoped
    public EntityManagerFactory create() {
        return Persistence.createEntityManagerFactory("DiveLog");
    }

    /**
     * Disposes of the give factory.
     * 
     * @param factory
     *            factory to dispose of
     */
    public void destroy(@Disposes final EntityManagerFactory factory) {
        factory.close();
    }
}
