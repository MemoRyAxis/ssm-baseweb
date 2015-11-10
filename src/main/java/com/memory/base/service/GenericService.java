package com.memory.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * base service interface
 *
 * @author memoryaxis@gmail.com
 * @date Oct 27, 2015
 */
public interface GenericService<E, PK extends Serializable> {

    int add(E entity);

    int addAll(E entity);

    int del(E entity);

    int delById(PK id);

    int update(E entity);

    int updateAll(E entity);

    E getById(PK id);

    E getOne(E entity);

    List<E> getAll(Map<String, Object> params);

    List<E> getAll(E entity);

    int getCount(Map<String, Object> params);

    int getCount(E entity);
}
