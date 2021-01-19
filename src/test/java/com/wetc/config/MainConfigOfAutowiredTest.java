package com.wetc.config;

import com.wetc.bean.Boss;
import com.wetc.bean.Car;
import com.wetc.bean.Color;
import com.wetc.dao.TestDao;
import com.wetc.service.TestService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/24 0:23
 * @Desc
 */
public class MainConfigOfAutowiredTest {

    @SuppressWarnings("resource")
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        getBeansName(applicationContext);
        TestService testService = applicationContext.getBean(TestService.class);
        System.out.println(testService);
        TestDao testDao = applicationContext.getBean(TestDao.class);
        System.out.println(testDao);

        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);

        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);

        applicationContext.close();
    }

    public void getBeansName(AnnotationConfigApplicationContext applicationContext ) {
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

}