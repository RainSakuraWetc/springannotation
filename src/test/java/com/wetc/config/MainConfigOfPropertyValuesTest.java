package com.wetc.config;

import com.wetc.bean.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/24 0:00
 * @Desc
 */
public class MainConfigOfPropertyValuesTest {

    @SuppressWarnings("resource")
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        getBeansName(applicationContext);
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }

    public void getBeansName(AnnotationConfigApplicationContext applicationContext ) {
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

}