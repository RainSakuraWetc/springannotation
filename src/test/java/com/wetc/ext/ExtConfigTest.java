package com.wetc.ext;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/29 22:42
 * @Desc
 */
public class ExtConfigTest {
    @SuppressWarnings("resource")
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);

        applicationContext.close();
    }
}