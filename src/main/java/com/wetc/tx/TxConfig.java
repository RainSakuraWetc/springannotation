package com.wetc.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 环境搭建
 *  1.导入相关依赖，
 *      数据源，数据库驱动，spring-jdbc模块
 *  2.配置数据源，JdbcTemplate
 *
 *  3.事务
 *      1.给方法标注@Transactional注解
 *      2.开启事务管理功能：@EnableTransactionManagement
 *      3.配置事务管理器管理事务：
 *          @Bean
 *          public PlatformTransactionManager transactionManager()
 * 原理：源码分析
 *  1.@EnableTransactionManagement
 *      利用TransactionManagementConfigurationSelector给容器中导入组件
 *      导入两个组件：AutoProxyRegistrar、ProxyTransactionManagementConfiguration
 *  2.AutoProxyRegistrar
 *      给容器中注册一个组件：InfrastructureAdvisorAutoProxyCreator
 *          1.利用后置处理器机制，在对象创建以后，包装对象，返回一个代理对象，代理对象利用拦截器链执行方法
 *  3.ProxyTransactionManagementConfiguration
 *      1.给容器中注册事务增强器：transactionAdvisor
 *          1.事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource解析事务注解的各个属性
 *          2.事务拦截器 transactionInterceptor
 *              保存了事务的属性信息，事务管理器，底层实现了MethodInterceptor，在目标方法执行的时候，执行拦截器链
 *                  1.获取事务相关的属性
 *                  2.获取PlatformTransactionManager，最终事先没有添加指定任何TransactionManager，最终
 *                      会从容器获取按照类型获取PlatformTransactionManager
 *                  3.执行目标方法
 *                      如果异常，获取事务管理器，利用事务管理器回滚
 *                      如果异常，获取事务管理器，利用事务管理器提交事务
 *
 *
 *
 *
 *
 *
 *
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/27 23:26
 * @Desc 声明式事务
 */
@EnableTransactionManagement
@ComponentScan("com.wetc.tx")
@Configuration
public class TxConfig {

    @Value("${db.username}")
    private String user;

    @Value("${db.password}")
    private String pwd;

    @Value("${db.driverClass}")
    private String driverClass;

    @Bean("testDataSource")
    public DataSource dataSourceTest() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {

        //spring对@Configuration类会特殊处理，给容器中加组件，多次调用只是从容器中找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceTest());
        return jdbcTemplate;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSourceTest());
    }
}
