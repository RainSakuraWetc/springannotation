package com.wetc.config;

import com.wetc.bean.Color;
import com.wetc.bean.ColorBeanFactory;
import com.wetc.bean.Person;
import com.wetc.condition.LunixCondition;
import com.wetc.condition.MyImportBeanDefinitionRegistrar;
import com.wetc.condition.MyImportSelector;
import com.wetc.condition.WindowCondition;
import org.springframework.context.annotation.*;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 14:29
 * @Desc
 */
//类中组件统一设置，满足当前条件，这个类中配置的所有bean才注册生效
@Conditional(WindowCondition.class)
@Configuration
@Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig1 {

    /**
     * 1.bean的实例
     * 	ConfigurableBeanFactory#SCOPE_PROTOTYPE
     * 	ConfigurableBeanFactory#SCOPE_SINGLETON
     * 	org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST
     * 	org.springframework.web.context.WebApplicationContext#SCOPE_SESSION
     *
     *  prototype:多实例：ioc容器启动并不会调用方法创建，而是每次获取时候才会调用方法创建对象
     *  singleton:单实例(默认值)：ioc容器启动会调用方法创建对象放到容器中，以后每次获取都从容器中拿
     *  request:同一个请求创建一次实例
     *  session:同一个session创建一次实例
     *
     * 2.懒加载
     *  单实例的bean，默认在容器启动的时候创建对象
     *  但是在懒加载的情况下，容器启动不创建对象，第一次使用的时候创建对象，并初始化。
     *
     *
     *
     *
     */
    @Lazy
    @Scope("singleton")
    @Bean
    public Person person() {
        return new Person("li1",20);
    }

    /**
     * 3.@Conditional:按条件注册bean
     *  按照操作系统环境，注册不同的bean，需要自定义规则，实现Condition接口
     */

    @Conditional(WindowCondition.class)
    @Bean("bill")
    public Person person01() {
        return new Person("bill",20);
    }

    @Conditional(LunixCondition.class)
    @Bean("linus")
    public Person person02() {
        return new Person("linus",20);
    }

    /**
     *
     * 4.给容器中注册组件
     *  1) 包扫描+组件标注注解(@Controller @Service @Repository @Component)
     *  2) @Bean[导入的第三方里面的组件]
     *  3) @Import[快速导入一个组件]
     *      1.@Import (要导入到容器的组件)：容器会自动注册这个组件，id默认是全类名
     *      2.ImportSelector:返回要导入的组件的全类名的数组
     *      3.ImportBeanDefinitionRegistrar
     *  4) 使用spring的FactoryBean
     *      工厂bean获取的是调用getObject创建的对象，非colorBeanFactory而是om.wetc.bean.Color
     *      如果想要获取工厂bean本身，即id为colorBeanFactory，需要如此getBean(&ColorBeanFactory)
     */
    @Bean
    public ColorBeanFactory colorBeanFactory() {
        return new ColorBeanFactory();
    }
}
