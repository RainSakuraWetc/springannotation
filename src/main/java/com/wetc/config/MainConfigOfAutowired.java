package com.wetc.config;

import com.wetc.bean.Car;
import com.wetc.bean.Color;
import com.wetc.dao.TestDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配
 *      spring利用依赖注入，完成对IOC容器中各个组件的依赖关系进行赋值
 *  1.@AutoWired---spring定义的
 *      1.1 默认按照类型去容器中找到对应的组件
 *          applicationContext.getBean(TestDao.class);
 *      1.2 如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 *          applicationContext.getBean("testDao");
 *      1.3 使用@Qualifier，指定要装配的组件id，而不是用属性名
 *      1.4 自动装配默认一定要将属性赋值好，否则没有就会报错
 *          使用@Autowired(required = false)，required调整为非必须
 *      1.5 @Primary 让spring自动装配时，默认使用首选的bean
 *  2.@Resouce(JSR250)、@Inject(JSR330)---Java规范
 *      1.1 @Resouce:默认是按照名称来装配的，不支持@Primary、required参数
 *      1.2 @Inject:需要导入javax.inject，和Autowired功能一致，只是无required参数
 *
 *  都是使用：AutowiredAnnotationBeanPostProcessor完成自动装配功能
 *
 *  -----------@AutoWired的其他使用-----------
 *  1.标注在方法上，spring容器创建当前对象，就会调用方法完成赋值
 *    方法使用的参数，自定义类型从的值从IOC容器中获取
 *          @Bean 标注的方法创建对象时，方法参数的值从容器中获取,默认不写@AutoWired
 *
 *  2.标注在在构造器上，构造器要用的组件，都是从容器中获取
 *    如果有参构造器只有一个参数，参数上的@AutoWired可以省略
 *
 *  3.标在参数上，效果是一样的，
 *
 *  4.都是从容器中获取参数组件的值
 *
 *  -----------自定义组件使用spring容器底层的一些组件-----------
 *  1.自定义组件实现xxxAware接口，类似一个回调函数，在创建对象的时候会调用接口规定的方法注入相关组件
 *
 *  2.把spring底层的组件注入到自定义的bean中：
 *      ApplicationContextAware:
 *      BeanNameAware:
 *      EmbeddedValueResolverAware:
 *  3.xxxAware的功能使用xxxProcessor实现：
 *
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 23:59
 * @Desc 自动注入相关的配置类
 */
@Configuration
@ComponentScan({
        "com.wetc.controller","com.wetc.service","com.wetc.dao","com.wetc.bean"
})
public class MainConfigOfAutowired {

    @Primary
    @Bean("testDao2")
    public TestDao testDao() {
        TestDao testDao = new TestDao();
        testDao.setLabel("2");
        return testDao;
    }

    /**
     * @Bean 标注的方法创建对象时，方法参数的值从容器中获取
     * @param car
     * @return
     */
    @Bean
    public Color color(Car car) {
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
