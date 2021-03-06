/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.dao.CrudDao;
import hu.gaborkolozsy.timeclock.dao.impl.CrudDaoImpl;
import hu.gaborkolozsy.timeclock.service.CrudService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * The basic <strong>C.R.U.D.</strong> generic service implementation dependent 
 * with persistence context.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <T> type of entity
 * @param <K> type of primary key
 * @since 0.0.1-SNAPSHOT
 * @see CrudDao
 * @see CrudDaoImpl
 * @see List
 */
@Transactional
public class CrudServiceImpl<T, K extends Serializable> implements CrudService<T, K> {

    @Autowired
    private final CrudDao<T, K> crudDao;
    
    /**
     * Constructor wait a {@code CrudDaoImpl} instance but with interface type.
     * 
     * <p><strong>So can avoid the {@link NoSuchBeanDefinitionException} 
     * exception!!!</strong>
     * @param crudDao {@code CrudDaoImpl}
     */
    public CrudServiceImpl(CrudDao<T, K> crudDao) {
        this.crudDao = crudDao;
    }

    /**
     * Make an instance, managed and persistent.
     * @param entity entity instance
     */
    @Override
    public void save(T entity) {
        crudDao.save(entity);
    }

    /**
     * Find by primary key.
     * Search for an entity of (the specified class and) primary key.
     * If the entity instance is contained in the persistence context,
     * it is returned from there.
     * @param primaryKey entity's primary key(ID)
     * @return the found entity instance or null if the entity does not exist
     */
    @Override
    public T get(K primaryKey) {
        return crudDao.get(primaryKey);
    }

    /**
     * Returns a list of the {@code TimeClock} entity.
     * @return a list of entity
     */
    @Override
    public List<T> getAll() {
        return (List<T>) crudDao.getAll();
    }
    
    /**
     * Merge the state of the given entity into the current persistence context.
     * @param entity entity instance 
     */
    @Override
    public <S extends T> S update(S entity) {
        return crudDao.update(entity);
    }

    /**
     * Remove the specified entity instance.
     * @param entity entity instance
     */
    @Override
    public void remove(T entity) {
        crudDao.remove(entity);
    }
    
    /**
     * Remove all entity instance.
     */
    @Override
    public void removeAll() {
        crudDao.removeAll();
    }
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param primarykey entity's primary key(ID)
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isExist(K primarykey) {
        return crudDao.isExist(primarykey);
    }
    
    /**
     * Check if the instance is a managed entity instance belonging to the 
     * current persistence context.
     * @param entity entity
     * @return boolean indicating if entity is in persistence context
     */
    @Override
    public boolean isExistEntity(T entity) {
        return crudDao.isExistEntity(entity);
    }

    /**
     * Clear persistence context.
     */
    @Override
    public void clear() {
        crudDao.clear();
    }

    /**
     * Close the entity manager.
     */
    @Override
    public void close() {
        crudDao.close();
    }

}
