package com.memory.base.persistence;

import com.memory.base.model.BaseModel;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;

/**
 * base DAO
 *
 * 1. all base DAO must extend {@code MyBatisDao} when using multiply data sources
 *
 * 2. every base DAO should use {@code @Qualifier} to get opposite session factory
 *
 * 3. every module's DAO layer must extend opposite base DAO
 *
 * @author memoryaxis@gmail.com
 * @date Oct 27, 2015
 */
public abstract class BaseDao<E extends BaseModel, PK extends Serializable> extends MyBatisDao<E, PK> {

    @Override
    @Autowired
    @Qualifier("baseSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
        super.setSqlSessionTemplate(new SqlSessionTemplate(sqlSessionFactory));
    }
}
