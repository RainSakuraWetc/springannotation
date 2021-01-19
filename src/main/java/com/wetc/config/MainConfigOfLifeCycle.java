package com.wetc.config;

import com.wetc.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean的生命周期：
 *      bean的创建---初始化---销毁
 * 容器管理bean的生命周期
 *      自定义初始化和销毁方法，
 * 配置指定初始化和销毁方法
 *      init-method destory-method
 * 1.构造(对象创建)
 *      单实例：在容器启动的时候创建
 *      多实例：在每次获取的时候创建
 *
 *   BeanPostProcessor.postProcessBeforeInitialization--初始化前
 *
 * 2.初始化
 *      对象创建完成，并赋值好，调用初始化方法
 *
 *   BeanPostProcessor.postProcessAfterInitialization--初始化后
 *
 * 3.销毁
 *      容器关闭的时候进行销毁
 *      多实例的bean不会销毁，容器
 *
 * -----------------------------------------------------
 * bean的初始化和销毁
 *  1.指定初始化和销毁的方法
 *      通过@Bean指定initMethod和destroyMethod
 *  2.通过让bean实现InitializingBean、DisposableBean接口
 *  3.可以使用JSR250：
 *      @PostConstruct 在bean创建完成并且属性赋值完成，执行初始化
 *      @PreDestroy 在容器销毁bean之前通知进行处理工作
 *  4.BeanPostProcessor,bean的后置处理器
 *      在bean初始化前后进行一些处理工作
 *      postProcessBeforeInitialization:初始化之前调用
 *      postProcessAfterInitialization:初始化之后调用
 *
 * -------------简单分析BeanPostProcessor工作原理-------------
 *
 * populateBean(beanName, mbd, instanceWrapper);给bean进行属性赋值
 *
 * initializeBean方法中：
 *      1.wrappedBean = this.applyBeanPostProcessorsBeforeInitialization(bean, beanName);
 *          遍历得到容器中所有的BeanPostProcessor，挨个执行postProcessBeforeInitialization
 *          一旦返回null，跳出for循环，不会执行后面的postProcessBeforeInitialization
 *
 *      2.this.invokeInitMethods(beanName, wrappedBean, mbd);---执行初始化
 *
 *      3.wrappedBean = this.applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *
 * -------------spring容器对BeanPostProcessor的应用-------------
 * ApplicationContextAwarePostProcessor
 * BeanValidationPostProcessor
 * InitDestroyAnnotationBeanPostProcessor:处理@PostConstruct、@PreDestroy注解
 * AutowiredAnnotationBeanPostProcessor：处理@AutoWired
 * ....
 *
 * bean赋值、注入其他组件、@AutoWired、生命周期注释功能、@Async.....
 *
 *
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 17:35
 * @Desc bean生命周期
 */
@Configuration
@ComponentScan("com.wetc.bean")
public class MainConfigOfLifeCycle {

    @Bean(initMethod = "init", destroyMethod = "destory")
    public Car car() {
        return new Car();
    }
}
