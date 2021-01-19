package com.wetc.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Profile:
 *      Spring为我们提供的可以根据当前环境，动态激活和切换一系列的组件功能
 *
 * 1.@Profile:指定组件在那个环境的情况下才会被注册到容器中
 * 2.加了环境标识的bean，只有环境被激活的时候才能被注册到容器中，默认是default环境
 *      1.切换环境：
 *          1.在虚拟机参数设置使用命令行参数：-Dspring.profiles.active=test
 *          2.代码方式
 *              2.1 使用无参构造器：new AnnotationConfigApplicationContext();
 *              2.2 设置要激活的环境 .....
 *      2.写在类上，只有指定的环境被激活，整个配置类中的所有配置才能生效
 *      3.没有标注环境标识的bean，在任何环境下都会加载
 *
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/24 17:05
 * @Desc
 */
@PropertySource(value = {"classpath:/dbconfig.properties"})
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    @Value("${db.username}")
    private String user;

    private StringValueResolver valueResolver;

    private String driverClass;

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("rpd")
    @Bean("prdDataSource")
    public DataSource dataSourcePrd(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver = resolver;
        this.driverClass = valueResolver.resolveStringValue("${db.driverClass}");
    }
}
