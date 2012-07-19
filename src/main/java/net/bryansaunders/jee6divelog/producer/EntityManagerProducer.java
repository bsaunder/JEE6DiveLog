/**
 * 
 */
package net.bryansaunders.jee6divelog.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Producer for EntityManagers.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class EntityManagerProducer {

    /**
     * Auto-Injected EntityManagerFactory.
     */
    @Inject
    private EntityManagerFactory emf;

    /**
     * Creates Entity Managers.
     * 
     * @return EntityManager
     */
    @Produces
    @ApplicationScoped
    public EntityManager create() {
        return this.emf.createEntityManager();
    }

    /**
     * Disposes of the given EntityManager.
     * 
     * @param em
     *            EntityManager to dispose of.
     */
    public void destroy(@Disposes final EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
}
