package com.memory.base.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * base DAO interfaces
 *
 * @author memoryaxis@gmail.com
 * @date Oct 27, 2015
 */
public interface GenericDao<E, PK extends Serializable> {

    // TODO return primary key
    Integer add(E entity);

    // TODO return primary key
    Integer addAll(E entity);

    Integer del(E entity);

    Integer delById(PK id);

    Integer update(E entity);

    Integer updateAll(E entity);

    E getById(PK id);

    E getOne(E entity);

    List<E> getAll(Map<String, Object> params);

    List<E> getAll(E entity);

    Integer getCount(Map<String, Object> params);

    Integer getCount(E entity);
}
