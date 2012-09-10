package net.bryansaunders.jee6divelog.dao;/* * #%L * BSNet-DiveLog * $Id:$ * $HeadURL:$ * %% * Copyright (C) 2012 Bryan Saunders * %% * This program is free software: you can redistribute it and/or modify * it under the terms of the GNU General Public License as * published by the Free Software Foundation, either version 3 of the  * License, or (at your option) any later version. *  * This program is distributed in the hope that it will be useful, * but WITHOUT ANY WARRANTY; without even the implied warranty of * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the * GNU General Public License for more details. *  * You should have received a copy of the GNU General Public  * License along with this program.  If not, see * <http://www.gnu.org/licenses/gpl-3.0.html>. * #L% */import java.lang.reflect.Field;import java.lang.reflect.ParameterizedType;import java.security.AccessController;import java.security.PrivilegedAction;import java.util.ArrayList;import java.util.List;import javax.ejb.Stateless;import javax.inject.Inject;import javax.persistence.EntityManager;import javax.persistence.NoResultException;import javax.persistence.TypedQuery;import javax.persistence.criteria.CriteriaBuilder;import javax.persistence.criteria.CriteriaQuery;import javax.persistence.criteria.Predicate;import javax.persistence.criteria.Root;import net.bryansaunders.jee6divelog.model.DiveLogEntity;import org.jboss.logging.Logger;/** * Implementation of Generic DAO. *  * @author Bryan Saunders <btsaunde@gmail.com> *  * @param <T> *            Entity type for DOA, Must exted DiveLogEntity */@Statelesspublic class GenericDaoImpl<T extends DiveLogEntity> implements GenericDao<T> {    /**     * Logger.     */    private final Logger logger = Logger.getLogger(GenericDaoImpl.class);    /**     * Entity Manager.     */    @Inject    private EntityManager entityManager;    /**     * Entity Class.     */    private final Class<T> entityClass;    /**     * Default Constructor.     */    @SuppressWarnings("unchecked")    public GenericDaoImpl() {        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];    }    /**     * {@inheritDoc}     */    @Override    public void setEntityManager(final EntityManager newEntityManager) {        this.entityManager = newEntityManager;    }    /**     * {@inheritDoc}     */    @Override    public EntityManager getEntityManager() {        return this.entityManager;    }    /**     * {@inheritDoc}     */    @Override    public T get(final Integer id) {        final T entity = this.entityManager.find(this.entityClass, id);        if (entity == null) {            throw new NoResultException("Entity Type: " + this.entityClass.getName() + " with ID: " + id                    + " Not Found.");        }        return entity;    }    /**     * {@inheritDoc}     */    @Override    public List<T> get(final Integer... ids) {        if (ids == null || ids.length == 0) {            throw new IllegalArgumentException("ID List must not be null and have atleast one element");        }        final List<T> entityList = new ArrayList<T>(ids.length);        for (final Integer id : ids) {            final T entity = this.get(id);            entityList.add(entity);        }        return entityList;    }    /**     * {@inheritDoc}     */    @Override    public List<T> getAll() {        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.entityClass);        final Root<T> root = criteriaQuery.from(this.entityClass);        criteriaQuery.select(root);        final TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);        return query.getResultList();    }    @Override    public T save(final T object) {        T savedObject = null;        if (object.getId() != null) {            this.logger.debug("Merging Object " + this.entityClass.getName() + " with ID " + object.getId());            savedObject = this.entityManager.merge(object);        } else {            this.logger.debug("Persisting Object " + this.entityClass.getName());            this.entityManager.persist(object);            savedObject = object;            this.logger.debug("Persisted with ID " + savedObject.getId());        }        return savedObject;    }    /**     * {@inheritDoc}     */    @Override    public List<T> save(final T... objects) {        if (objects == null || objects.length == 0) {            throw new IllegalArgumentException("Object List must not be null and have atleast one element");        }        final List<T> entityList = new ArrayList<T>(objects.length);        for (final T entity : objects) {            final T savedEntity = this.save(entity);            entityList.add(savedEntity);        }        return entityList;    }    /**     * {@inheritDoc}     */    @Override    public void delete(final Integer id) {        final T entity = this.get(id);        this.delete(entity);    }    /**     * {@inheritDoc}     */    @Override    public void delete(final Integer... ids) {        if (ids == null || ids.length == 0) {            throw new IllegalArgumentException("Object List must not be null and have atleast one element");        }        for (final Integer id : ids) {            this.delete(id);        }    }    /**     * {@inheritDoc}     */    @Override    public void delete(final T object) {        this.entityManager.remove(object);        this.logger.debug("Deleting Object " + this.entityClass.getName() + " with ID " + object.getId());    }    /**     * {@inheritDoc}     */    @Override    public void delete(final T... objects) {        if (objects == null || objects.length == 0) {            throw new IllegalArgumentException("Object List must not be null and have atleast one element");        }        for (final T entity : objects) {            this.delete(entity);        }    }    /**     * {@inheritDoc}     */    @Override    public void deleteAll() {        final List<T> entities = this.getAll();        for (final T entity : entities) {            this.delete(entity);        }    }    /**     * {@inheritDoc}     */    @Override    public List<T> findByExample(final T example) {        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();        // Build the Query        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.entityClass);        final Root<T> root = criteriaQuery.from(this.entityClass);        // Select all Columns        criteriaQuery.select(root);        // Build Where Clause        Predicate predicate = criteriaBuilder.conjunction();        final Field[] fields = this.entityClass.getDeclaredFields();        for (final Field field : fields) {            final String fieldName = field.getName();            // So that we ignore fields like jacocoData that get added by jacoco            if (fieldName.startsWith("$")) {                continue;            }            Object fieldValue = null;            try {                AccessController.doPrivileged(new PrivilegedAction<Object>() {                    public Object run() {                        field.setAccessible(true);                        return null;                    }                });                fieldValue = field.get(example);            } catch (final IllegalArgumentException e) {                this.logger.warn(                        "findByExample Skipping Field " + fieldName + " on Object Type " + this.entityClass.getName()                                + ".", e);                continue;            } catch (final IllegalAccessException e) {                this.logger.warn(                        "findByExample Skipping Field " + fieldName + " on Object Type " + this.entityClass.getName()                                + ".", e);                continue;            }            if (fieldValue != null) {                this.logger.info("Setting Where: " + fieldName + " = " + fieldValue.toString());                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(fieldName), fieldValue));            }        }        criteriaQuery.where(predicate);        final TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);        this.logger.info("QUERY: " + query.toString());        return query.getResultList();    }}