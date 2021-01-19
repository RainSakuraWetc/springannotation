package com.wetc.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 12:05
 * @Desc
 */
public class MainConfigTest {

    @SuppressWarnings("resource")
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        getBeansName(applicationContext);
    }

    @SuppressWarnings("resource")
    @Test
    public void test1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig1.class);
        Object person1 = applicationContext.getBean("person");
        Object person2 = applicationContext.getBean("person");

    }

    @SuppressWarnings("resource")
    @Test
    public void test2() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig1.class);
        getBeansName(applicationContext);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String s = environment.getProperty("os.name");
    }

    @SuppressWarnings("resource")
    @Test
    public void testImport() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig1.class);
        getBeansName(applicationContext);

        Object colorBeanFactory1 = applicationContext.getBean("colorBeanFactory");
        Object colorBeanFactory2 = applicationContext.getBean("&colorBeanFactory");

        System.out.println(colorBeanFactory1);
        System.out.println(colorBeanFactory2);

    }


    @SuppressWarnings("resource")
    @Test
    public void test3() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成...");

        //关闭容器
        applicationContext.close();
    }


    public void getBeansName(AnnotationConfigApplicationContext applicationContext ) {
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}