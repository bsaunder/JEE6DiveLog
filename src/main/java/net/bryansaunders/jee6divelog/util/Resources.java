package net.bryansaunders.jee6divelog.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Produces application Resources.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class Resources {

    /**
     * Entity Manager.
     */
    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext
    private EntityManager em;

}
