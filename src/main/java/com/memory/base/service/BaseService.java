package com.memory.base.service;

import com.memory.base.model.BaseModel;
import com.memory.base.persistence.BaseDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * base service
 *
 * @author memoryaxis@gmail.com
 * @date Oct 27, 2015
 */
public abstract class BaseService<E extends BaseModel, PK extends Serializable>
        implements
        GenericService<E, PK> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract BaseDao<E, PK> getBaseDao();

    @Override
    @Transactional
    public int add(E entity) {
        return getBaseDao().add(entity);
    }

    @Override
    @Transactional
    public int addAll(E entity) {
        return getBaseDao().addAll(entity);
    }

    @Override
    @Transactional
    public int del(E entity) {
        return getBaseDao().del(entity);
    }

    @Override
    @Transactional
    public int delById(PK id) {
        return getBaseDao().delById(id);
    }

    @Override
    @Transactional
    public int update(E entity) {
        return getBaseDao().update(entity);
    }

    @Override
    @Transactional
    public int updateAll(E entity) {
        return getBaseDao().updateAll(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public E getById(PK id) {
        return getBaseDao().getById(id);
    }

    @Override
    @Deprecated
    @Transactional(readOnly = true)
    public E getOne(E entity) {
        return getBaseDao().getOne(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> getAll(Map<String, Object> params) {
        return getBaseDao().getAll(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> getAll(E entity) {
        return getBaseDao().getAll(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public int getCount(Map<String, Object> params) {
        return getBaseDao().getCount(params);
    }

    @Override
    @Transactional(readOnly = true)
    public int getCount(E entity) {
        return getBaseDao().getCount(entity);
    }
}
