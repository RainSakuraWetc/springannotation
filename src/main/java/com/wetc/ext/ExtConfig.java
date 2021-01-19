package com.wetc.ext;

import com.wetc.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 扩展原理
 *      BeanPostProcessor:bean的后置处理器，bean创建对象初始化前后进行拦截工作
 *  1.BeanFactoryPostProcessor
 *      beanFactory的后置处理器，该接口中有个方法BeanFactoryPostProcessor
 *      执行实际在，在标准初始化之后调用(所有的bean定义被加载但是没有bean被初始化)
 *      流程：
 *          1.ioc容器创建对象
 *          2.invokeBeanFactoryPostProcessors(beanFactory);
 *          3.跟BeanPostProcessor类似，从beanFactory中找到所有的BeanFactoryPostProcessors组件，
 *              并根据类型分类执行invokeBeanFactoryPostProcessors
 *
 *  2.BeanDefinitionRegistryPostProcessor
 *      是BeanFactoryPostProcessor的子接口，该接口中多一个方法postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
 *          --->BeanDefinitionRegistry就是bean定义信息的存储中心，以后beanfactory就是按照BeanDefinitionRegistry
 *              里面保存的每一个定义信息创建bean实例的
 *      执行实际是在，所有bean定义信息将要被加载但是bean实例还未并创建，在BeanFactoryPostProcessor前执行
 *          可以利用BeanDefinitionRegistryPostProcessor给容器中额外添加组件
 *      流程：
 *          1.ioc容器创建对象
 *          2.invokeBeanFactoryPostProcessors(beanFactory);
 *          3.从容器中获取到所有的BeanDefinitionRegistryPostProcessor组件，
 *              1.按照类型分类执行invokeBeanDefinitionRegistryPostProcessors(orderedPostProcessors, registry);
 *              2.再分类执行invokeBeanFactoryPostProcessors
 *          4.接着就从beanFactory中找到所有的BeanFactoryPostProcessors组件，
 *              并根据类型分类执行invokeBeanFactoryPostProcessors
 *
 *  3.ApplicationListener:应用监听器，监听容器中发布的时间，事件驱动模型的开发
 *      public interface ApplicationListener<E extends ApplicationEvent>
 *          监听ApplicationEvent以及下面的子事件
 *
 *      监听事件步骤：
 *          1.写一个监听器来监听某个事件：ApplicationEvent及其子类
 *              或者使用注解来@EventListener
 *                  原理：使用EventListenerMethodProcessor来解析方法上的@EventListener注解
 *                      1.实现了SmartInitializingSingleton接口，原理如下
 *
 *          2.把监听器放入到容器中
 *          3.只要容器中有相关事件发布，就能监听到这个事件
 *              ContextRefreshedEvent
 *              ContextClosedEvent
 *          4.发布事件
 *              applicationContext.publishEvent
 *      工作原理：
 *          1.ContextRefreshedEvent、applicationContext.publishEvent事件、ContextClosedEvent
 *              1.容器创建对象，refresh()刷新对象
 *              2.finishRefresh()-->容器刷新完成，最后一步
 * >事件发布流程 3.publishEvent(new ContextRefreshedEvent(this));
 *              4.getApplicationEventMulticaster().multicastEvent(applicationEvent, eventType);
 *                  1.获取事件多播器(派发器)
 *                  2.multicastEvent事件派发
 *                      1.获取到所有的ApplicationListener
 *                          1.如果有executor进行异步派发
 *                          2.否则同步的方式直接执行invokeListener(listener, event);方法
 *                              --->doInvokeListener(listener, event)--->listener.onApplicationEvent(event);
 *                              即回调了onApplicationEvent方法
 *
 *-------事件多播器的原理-------
 *  1.容器创建对象，refresh()
 *  2.initApplicationEventMulticaster();初始化ApplicationEventMulticaster
 *      1.先去容器找有没有id为applicationEventMulticaster的组件
 *          如果有就直接获取，如果没有就创建一个
 *      2. new SimpleApplicationEventMulticaster(beanFactory);并注册到容器中，
 *          就可以在其他组件要派发事件时，自动注入applicationEventMulticaster
 *
 *-----------容器中有哪些监听器listener---------------
 *  1.容器创建对象，refresh()
 *  2.registerListeners();注册监听器
 *      1.从容器中获取到所有的监听器，
 *          String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 *      2.把他们注册到applicationEventMulticaster中
 *          getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *
 *------------------SmartInitializingSingleton原理------------------
 *  afterSingletonsInstantiated的实现
 *  1.容器创建对象，refresh()
 *  2.finishBeanFactoryInitialization(beanFactory);初始化剩下的单实例bean
 *      1.先创建所有的单实例bean
 *      2.获取所有创建好的单实例bean，判断是否是SmartInitializingSingleton类型的
 *          如果是就调用afterSingletonsInstantiated
 *
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/29 22:37
 * @Desc
 */
@Configuration
@ComponentScan("com.wetc.ext")
public class ExtConfig {

    @Bean
    public Blue blue() {
        return new Blue();
    }
}
