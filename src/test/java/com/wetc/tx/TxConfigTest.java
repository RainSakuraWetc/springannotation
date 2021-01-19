package com.wetc.tx;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/27 23:40
 * @Desc
 */
public class TxConfigTest {

    @SuppressWarnings("resource")
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertUser();
        applicationContext.close();
    }

}