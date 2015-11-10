package com.memory.user.service;

import com.memory.base.persistence.BaseDao;
import com.memory.base.service.BaseService;
import com.memory.user.dao.UserDao;
import com.memory.user.po.User;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author memoryaxis@gmail.com
 * @date 2015/11/9 10:40
 */
@Service
public class UserService extends BaseService<User, Long> {

    @Autowired
    UserDao userDao;

    @Override
    public BaseDao<User, Long> getBaseDao() {
        return this.userDao;
    }

    @Transactional
    public void getAdd() throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(DigestUtils.md5Hex("123456"));
        userDao.add(user);
    }

}
