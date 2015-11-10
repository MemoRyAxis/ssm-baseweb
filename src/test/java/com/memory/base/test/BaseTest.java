package com.memory.base.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * extends this class to load Spring context
 *
 * need annotation {@code @Transactional} & {@code @Rollback} if need controller transactions
 *
 * @author memoryaxis@gmail.com
 * @date 2015/11/9 11:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class BaseTest {

}
