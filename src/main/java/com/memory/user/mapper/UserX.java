package com.memory.user.mapper;

import com.memory.user.po.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 
 * @author  memoryaxis@gmail.com
 * @date    2015/11/7 16:57
 */
//@Repository
public interface UserX {

//    @Transactional(readOnly = true)
    int add(User user);
}
