package net.bryansaunders.jee6divelog.dao;

import java.util.List;

import javax.persistence.EntityManager;

import net.bryansaunders.jee6divelog.model.DiveLogEntity;

/**
 * Generic DAO class.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 * @param <T>
 *            Entity type for DOA, Must exted DiveLogEntity
 */
public interface GenericDao<T extends DiveLogEntity> {

    /**
     * Set entity manager.
     * 
     * @param newEntityManager
     *            entity manager
     */
    void setEntityManager(EntityManager newEntityManager);

    /**
     * Get the entityManager.
     * 
     * @return the entityManager
     */
    EntityManager getEntityManager();

    /**
     * Retrieve an persisted object using the given id as primary key.
     * 
     * Throws EntityNotFoundException if if entity is not found.
     * 
     * @param id
     *            object's primary key
     * @return object
     */
    T get(Integer id);

    /**
     * Retrieve an persisted objects using the given ids as primary keys.
     * 
     * EntityNotFoundException thrown if the entity is not found, IllegalArgumentException thrown when the ID list is
     * invalid
     * 
     * @param ids
     *            objects's ids
     * @return list of objects
     */
    List<T> get(Integer... ids);

    /**
     * Retrieve all persisted objects.
     * 
     * @return list of objects
     */
    List<T> getAll();

    /**
     * Save all changes made to an object.
     * 
     * @param object
     *            object
     * @return The saved object
     */
    T save(T object);

    /**
     * Save all changes made to objects. IllegalArgumentException thrown when the ID list is
     * invalid
     * 
     * @param objects
     *            objects
     * @return list of saved objects
     */
    List<T> save(T... objects);

    /**
     * Remove an object by given id.
     * 
     * @param id
     *            object's pk
     */
    void delete(Integer id);

    /**
     * Remove objects by given ids. IllegalArgumentException thrown when the ID list is
     * invalid
     * 
     * @param ids
     *            objects's pk
     */
    void delete(Integer... ids);

    /**
     * Remove an object.
     * 
     * @param object
     *            object to delete
     */
    void delete(T object);

    /**
     * Remove objects. IllegalArgumentException thrown when the ID list is
     * invalid
     * 
     * @param objects
     *            objects to delete
     */
    void delete(T... objects);

    /**
     * Delete all objects.
     */
    void deleteAll();
}
