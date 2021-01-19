package com.wetc.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/24 23:04
 * @Desc
 */
public class MainConfigOfProfileTest {

    @SuppressWarnings("resource")
    @Test
    public void test() {
        // AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

        // 1.创建无参的applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 2.设置要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test","dev");

        // 3.注册配置类
        applicationContext.register(MainConfigOfProfile.class);

        // 4.刷新容器
        applicationContext.refresh();

        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }


        applicationContext.close();
    }

}