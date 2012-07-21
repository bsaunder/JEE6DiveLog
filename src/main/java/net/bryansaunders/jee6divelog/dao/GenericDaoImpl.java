package net.bryansaunders.jee6divelog.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.bryansaunders.jee6divelog.model.DiveLogEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of Generic DAO.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 * @param <T>
 *            Entity type for DOA, Must exted DiveLogEntity
 */
@Stateless
public class GenericDaoImpl<T extends DiveLogEntity> implements GenericDao<T> {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);

    /**
     * Entity Manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Entity Class.
     */
    private final Class<T> entityClass;

    /**
     * Default Constructor.
     */
    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntityManager(final EntityManager newEntityManager) {
        this.entityManager = newEntityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(final Integer id) {
        final T entity = this.entityManager.find(this.entityClass, id);
        if (entity == null) {
            throw new EntityNotFoundException("Entity Type: " + this.entityClass.getName() + " with ID: " + id
                    + " Not Found.");
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> get(final Integer... ids) {
        if (ids == null || ids.length == 0) {
            throw new IllegalArgumentException("ID List must not be null and have atleast one element");
        }

        final List<T> entityList = new ArrayList<T>(ids.length);
        for (final Integer id : ids) {
            final T entity = this.get(id);
            entityList.add(entity);
        }
        return entityList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.entityClass);
        Root<T> root = criteriaQuery.from(this.entityClass);
        criteriaQuery.select(root);
        
        final TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public T save(final T object) {
        T savedObject = null;

        if (object.getId() != null) {
            GenericDaoImpl.LOGGER.debug("Merging Object " + this.entityClass.getName() + " with ID " + object.getId());
            savedObject = this.entityManager.merge(object);
        } else {
            GenericDaoImpl.LOGGER.debug("Persisting Object " + this.entityClass.getName());
            this.entityManager.persist(object);
            savedObject = object;
            GenericDaoImpl.LOGGER.debug("Persisted with ID " + savedObject.getId());
        }

        return savedObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> save(final T... objects) {
        if (objects == null || objects.length == 0) {
            throw new IllegalArgumentException("Object List must not be null and have atleast one element");
        }

        final List<T> entityList = new ArrayList<T>(objects.length);
        for (final T entity : objects) {
            final T savedEntity = this.save(entity);
            entityList.add(savedEntity);
        }
        return entityList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Integer id) {
        final T entity = this.get(id);
        this.delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Integer... ids) {
        if (ids == null || ids.length == 0) {
            throw new IllegalArgumentException("Object List must not be null and have atleast one element");
        }

        for (final Integer id : ids) {
            this.delete(id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final T object) {
        this.entityManager.remove(object);
        GenericDaoImpl.LOGGER.debug("Deleting Object " + this.entityClass.getName() + " with ID " + object.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final T... objects) {
        if (objects == null || objects.length == 0) {
            throw new IllegalArgumentException("Object List must not be null and have atleast one element");
        }

        for (final T entity : objects) {
            this.delete(entity);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        final List<T> entities = this.getAll();
        for (final T entity : entities) {
            this.delete(entity);
        }
    }
}
