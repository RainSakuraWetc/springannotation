package com.wetc;

import com.wetc.bean.Person;
import com.wetc.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 10:47
 * @Desc
 */
public class MainTest {
    public static void main(String[] args) {
        /*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person pserson = (Person) context.getBean("pserson");
        System.out.println(pserson);*/

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);

        String[] names = applicationContext.getBeanNamesForType(Person.class);
        for (String name : names) {
            System.out.println(name);
        }
    }
}
