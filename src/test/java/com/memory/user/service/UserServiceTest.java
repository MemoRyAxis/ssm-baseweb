package com.memory.user.service;

import com.memory.base.test.BaseTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author memoryaxis@gmail.com
 * @date 2015/11/9 11:38
 */
@Transactional
public class UserServiceTest extends BaseTest {

    @Autowired
    UserService userService;

    @Test
    @Rollback(true)
    public void testTx() throws Exception {
        userService.getAdd();
    }

}