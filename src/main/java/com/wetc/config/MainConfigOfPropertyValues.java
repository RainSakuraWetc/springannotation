package com.wetc.config;

import com.wetc.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 23:59
 * @Desc 属性赋值相关的配置类
 */

//使用@PropertySource读取外部配置文件
@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValues {

    @Bean
    public Person person() {
        return new Person();
    }
}
