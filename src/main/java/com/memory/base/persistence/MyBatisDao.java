package com.memory.base.persistence;

import com.memory.base.model.BaseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * mybatis base DAO
 *
 * @author memoryaxis@gmail.com
 * @date Oct 27, 2015
 */
public abstract class MyBatisDao<E extends BaseModel, PK extends Serializable>
        extends MySqlSessionDaoSupport implements GenericDao<E, PK> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    // TODO get generic type
    protected abstract Class<E> getEntityClass();

    protected final AtomicBoolean verbose;

    public MyBatisDao() {
        verbose = new AtomicBoolean(false);
    }

    public String getStatement(String mapperId) {
        return getEntityClass().getCanonicalName() + '.' + mapperId;
    }

    @Override
    public Integer add(E entity) {

        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);

        return getSqlSession().insert(getStatement("add"), entity);
    }

    @Override
    public Integer addAll(E entity) {

        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);

        return getSqlSession().insert(getStatement("addAll"), entity);
    }

    @Override
    public Integer del(E entity) {
        return getSqlSession().delete(getStatement("del"), entity);
    }

    @Override
    public Integer delById(PK id) {
        return getSqlSession().delete(getStatement("delById"), id);
    }

    @Override
    public Integer update(E entity) {

        entity.setUpdateTime(new Date());

        return getSqlSession().update(getStatement("update"), entity);
    }

    @Override
    public Integer updateAll(E entity) {

        entity.setUpdateTime(new Date());

        return getSqlSession().update(getStatement("updateAll"), entity);
    }

    @Override
    public E getById(PK id) {
        return getSqlSession().selectOne(getStatement("getById"), id);
    }

    @Override
    public E getOne(E entity) {
        return getSqlSession().selectOne(getStatement("getAll"), entity);
    }

    @Override
    public List<E> getAll(Map<String, Object> params) {

        if (params.containsKey("pageNo") && params.get("pageNo") != null
                && params.containsKey("pageSize") && params.get("pageSize") != null) {
            int pageNo = (Integer) params.get("pageNo");
            int pageSize = (Integer) params.get("pageSize");
            params.put("start", (pageNo - 1) * pageSize);
            params.put("offset", pageSize);
        }

        return getSqlSession().selectList(getStatement("getAll"), params);
    }

    @Override
    public List<E> getAll(E entity) {

        if (entity.getPageNo() != null && entity.getPageSize() != null && entity.getPageNo() > 0
                && entity.getPageSize() > 0) {
            entity.setStart((entity.getPageNo() - 1) * entity.getPageSize());
            entity.setOffset(entity.getPageSize());
        }

        return getSqlSession().selectList(getStatement("getAll"), entity);
    }

    @Override
    public Integer getCount(Map<String, Object> params) {
        return getSqlSession().selectOne(getStatement("getCount"), params);
    }

    @Override
    public Integer getCount(E entity) {
        return getSqlSession().selectOne(getStatement("getCount"), entity);
    }
}
