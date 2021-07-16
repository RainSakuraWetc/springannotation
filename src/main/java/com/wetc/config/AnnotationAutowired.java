package com.wetc.config;

import com.wetc.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2021/3/16 18:18
 * @Desc
 */
public class AnnotationAutowired {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationAutowired.class);
        applicationContext.refresh();

        applicationContext.close();
    }

    @Autowired
    private User user;


    @Bean
    public User user(){
        return createUser("user1");
    }

    private static User createUser(String name){
        User user=new User();
        user.setName(name);
        return user;
    }
}
