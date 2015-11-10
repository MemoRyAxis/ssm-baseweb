package com.memory.user.dao;

import org.springframework.stereotype.Repository;

import com.memory.base.persistence.BaseDao;
import com.memory.user.po.User;

@Repository
public class UserDao extends BaseDao<User, Long> {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

}
