package com.wetc.config;

import com.wetc.aop.LogAspects;
import com.wetc.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP动态代理
 *      指在任务运行期间，动态的将某段代码切入到指定方法指定位置进行运行的编码方式
 *  1.导入依赖 spring-aspects
 *  2.定义业务类 MathCalculator
 *  3.定义日志切面类 LogAspects
 *      通知方法：前置通知(@Before)：目标方法运行前、
 *               后置通知(@After)：目标方法运行后、
 *               返回通知(@AfterReturning)：正常返回后通知
 *               异常通知(@AfterThrowing)：异常返回后通知
 *               环绕通知(@Around)：动态代理
 *  4.给切面类的目标方法注释何时何地运行
 *  5.将切面类和业务逻辑类都加入到容器中
 *  6.给切面类加注解，告诉spring这个是切面类
 *  7.开启基于注解的AOP模式：给配置类中加@EnableAspectJAutoProxy
 *      在spring中会有很多的@Enablexxx，来开启某项功能
 *
 *  总结步骤：
 *      1.将业务逻辑类和切面类加入到容器中，并指定切面类 @Aspect
 *      2.在切面类上的通知方法上标注通知注解，告诉spring何时运行(切入点表达式)
 *      3.开启基于注解的AOP模式，@EnableAspectJAutoProxy
 *
 *
 *  -----------------------------------AOP原理----------------------------------------
 *  总的来说就是：看给容器注册了什么组件，这个组件的功能是什么，什么时候工作
 *
 *  1.@EnableAspectJAutoProxy是什么
 *      1).@Import(AspectJAutoProxyRegistrar.class),给容器中导入AspectJAutoProxyRegistrar
 *              AspectJAutoProxyRegistrar：自定义给容器中注册bean
 *              internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *          结论是给容器中注入一个：AnnotationAwareAspectJAutoProxyCreator，自动代理创建器
 *  2.AnnotationAwareAspectJAutoProxyCreator
 *      -->AspectJAwareAdvisorAutoProxyCreator
 *          -->AbstractAdvisorAutoProxyCreator
 *              -->AbstractAutoProxyCreator
 *                  implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                  关注后置处理器，自动装配beanFactory
 *      2.1 分析AnnotationAwareAspectJAutoProxyCreator的父类中关于后置处理器，自动装配beanFactory的方法：
*               1)  AbstractAutoProxyCreator.setBeanFactory()
 *                  AbstractAutoProxyCreator中存在后置处理器
 *              2)  AbstractAdvisorAutoProxyCreator.setBeanFactory() --> initBeanFactory():
 *                  重写了父类的setBeanFactory,会调用initBeanFactory方法
 *              3)  AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *
 *      2.2 AnnotationAwareAspectJAutoProxyCreator流程分析---注册后置处理器
 *              1)  调用有参构造器，创建IOC容器
 *                      AnnotationConfigApplicationContext(Class<?>... annotatedClasses)
 *              2)  注册配置类，调用refresh()方法刷新容器
 *              3)  registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建
 *                      1)  先获取IOC容器中已经定义了的需要创建对象的所有的BeanPostProcessor
 *                      2)  给容器中加其他的BeanPostProcessor
 *                      3)  优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *                      4)  再给容器中注册Ordered接口的BeanPostProcessor
 *                      5)  注册没有实现优先级接口的BeanPostProcessor
 *                      6)  注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存到容器中
 *                              创建internalAutoProxyCreator的BeanPostProcessor,类型是AnnotationAwareAspectJAutoProxyCreator
 *                                  1)  创建bean的实例
 *                                  2)  populateBean(beanName, mbd, instanceWrapper);属性赋值
 *                                  3)  initializeBean(beanName, exposedObject, mbd);初始化bean
 *                                          1)  invokeAwareMethods(beanName, bean);处理Aware接口的回调
 *                                          2)  applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *                                                  应用后置处理器的postProcessBeforeInitialization方法
 *                                          3)  invokeInitMethods(beanName, wrappedBean, mbd);执行自定义的初始化方法
 *                                          4)  applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *                                                  执行后置处理器的postProcessAfterInitialization方法
 *                                  4)  AnnotationAwareAspectJAutoProxyCreator的BeanPostProcessor创建成功
 *                      7)  把BeanPostProcessor注册到BeanFactory中
 *                          beanFactory.addBeanPostProcessor(postProcessor)
 *
 *------------------------------以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程-----------------------------------
 *      两种类型的后置处理器：
 *          BeanPostProcessor是在bean对象创建完成初始换前后调用的处理器
 *          InstantiationAwareBeanPostProcessor是在创建bean实例之前先尝试调用后置处理器返回对象的
 *
 *          AnnotationAwareAspectJAutoProxyCreator.postProcessBeforeInstantiation
 *          *.postProcessBeforeInitialization
 *
 *              4)  完成BeanFactory初始化工作，创建剩下的单实例bean：finishBeanFactoryInitialization(beanFactory);
 *                      1)  遍历获取容器中的所有bean，依次创建对象getBean(beanName);
 *                              getBean(beanName) -> doGetBean -> getSingleton ->
 *                      2)  创建bean
 *
 *                              ---AnnotationAwareAspectJAutoProxyCreator会在所有bean创建之前拦截，因为是这个
 *                                 InstantiationAwareBeanPostProcessor后置处理器，会调用postProcessBeforeInstantiation
 *
 *                              1)  先从缓存中获取bean，如果能获取到，说明bean之前被创建过，直接使用，否则再创建
 *                              2)  createBean(beanName, mbd, args);创建bean
 *                                  -->结论：AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先返回bean的实例
 *                                          BeanPostProcessor是在bean对象创建完成初始换前后调用的处理器
 *                                          InstantiationAwareBeanPostProcessor是在创建bean实例之前先尝试调用后置处理器返回对象的
 *
 *                                      1)  resolveBeforeInstantiation(beanName, mbdToUse);解析BeforeInstantiation
 *                                          希望后置处理器在此能返回一个代理对象，如果不能
 *                                              1)  后置处理器先尝试返回对象
 *                                                  bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                                                      拿到所有的后置处理器，如果是InstantiationAwareBeanPostProcessor
 *                                                      调用postProcessBeforeInstantiation方法
 * 					                                if (bean != null) {
 * 						                                bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                                                  }
 *                                      2)  调用doCreateBean(beanName, mbdToUse, args);真正的创建bean实例，和3)6)流程一样
 *
 *-------------InstantiationAwareBeanPostProcessor后置处理器-----------------------
 *  分析本例MathCalculator和LogAspect的创建
 *  1)   调用postProcessBeforeInstantiation
 *          1)  判断当前bean是否在advisedBeans中(保存了所有要增强的bean)
 *          2)  判断当前bean是否是基础类型：Advice、Pointcut、Advisor、AopInfrastructureBean。或者是否是切面(@Aspect)
 *          3)  判断是否要跳过
 *                  1)  获取候选的增强器(切面里面的通知方法)：存放在集合List<Advisor> candidateAdvisors中
 *                      每一个封装的通知方法的增强器的类型是InstantiationModelAwarePointcutAdvisor:
 *                      判断每个增强器的类型是否是AspectJPointcutAdvisor类型：是则返回true
 *  2)  调用postProcessAfterInstantiation
 *          wrapIfNecessary(bean, beanName, cacheKey);//包装如果需要的话
 *              1)  获取bean的增强器(通知方法)：Object[] specificInterceptors
 *                      1)  找到候选的所有增强器(找哪些通知方法需要切入到当前bean方法的)
 *                      2)  获取到可以在当前bean使用的增强器
 *                      3)  给增强器排序
 *              2)  保存当前bean在advisedBeans，表示当前bean被增强处理
 *              3)  如果当前bean需要增强，创建当前bean的代理对象createProxy()
 *                      1)  获取所有的增强器
 *                      2)  保存到proxyFactory
 *                      3)  创建代理对象：spring决定
 *                              JdkDynamicAopProxy(config);
 *                              ObjenesisCglibAopProxy(config);
 *              4)  给容器中返回当前组件使用cglib增强的对象
 *              5)  以后从容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 *
 *  3)  目标方法的执行
 *          容器中保存了组件的代理对象，这个对象保存了详细信息(增强器，目标对象...)
 *          1)  拦截目标方法的执行CglibAopProxy.intercept()
 *                  1)  根据proxyFactory对象获取将要执行的拦截器链
 *                          List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *                          1)  创建interceptorList保存所有拦截器
 *                          2)  遍历所有的增强器，通过调用registry.getInterceptors(advisor)，将其转成 interceptors
 *                                  1)  如果类型是MethodInterceptor，直接转成MethodInterceptor并加入到interceptors集合中
 *                                  2)  如果类型不是MethodInterceptor，使用AdvisorAdapter将增强器转成MethodInterceptor，加入到集合中。
 *                          所谓的拦截器链，就是每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制执行
 *                  2)  如果没有拦截器链，直接执行目标方法
 *                  3)  如果有拦截器链，把需要执行的目标对象，方法，拦截器链等信息传入，创建CglibMethodInvocation对象调用proceed方法
 *                          retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
 *                  4)  拦截器链的执行过程
 *                          1)  利用索引记录当前拦截器的索引
 *                                  1)  如果没有拦截器执行目标方法，或者拦截器索引和拦截器数组-1大小一致(指定到了最好一个拦截器)
 *                          2)  按照索引的第0个索引位置的拦截器--ExposeInvocationInterceptor
 *                                  调用((MethodInterceptor) interceptorOrInterceptionAdvice).invoke(this);this就是CglibMethodInvocation对象
 *                                  先把this，也就是CglibMethodInvocation对象放到本地线程invocation中，然后再调用CglibMethodInvocation.proceed()
 *                                  方法
 *                                      ThreadLocal<MethodInvocation> invocation =
 *                                      new NamedThreadLocal<MethodInvocation>("Current AOP method invocation");
 *                          3)  此时进入第1个索引位置的拦截器，直接调用proceed方法，循环到索引为4也就是最后一个拦截器时，
 *                              调用前置通知，完成后调用目标方法invokeJoinpoint()，即调用前置通知。
 *                          4)  在索引为4的拦截器调用完，返回true之后，进入到索引为3的拦截器中，调用索引为3拦截器finnally摸模块中的后置通知方法，
 *                          5)  如果后置通知返回没问题，直接执行
 *                                  this.advice.afterReturning(retVal, mi.getMethod(), mi.getArguments(), mi.getThis());方法
 *                          6)  否则返回到AspectJAfterThrowingAdvice中，获取异常，执行异常通知方法，抛出异常
 *                                  if (shouldInvokeOnThrowing(ex)) {
 * 				                        invokeAdviceMethod(getJoinPointMatch(), null, ex);
 *                                  }
 *                  5)  链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再执行
 *                      通过拦截器链的机制，保证通知方法和目标方法的执行顺序
 *
 *---------------------------AOP总结------------------------------
 * 1)   @EnableAspectJAutoProxy 开启AOP功能
 * 2)   @EnableAspectJAutoProxy 会给容器注册一个组件AnnotationAwareAspectJAutoProxyCreator
 * 3)   AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 * 4)   利用容器的创建流程
 *          1)  registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建AnnotationAwareAspectJAutoProxyCreator
 *          2)  初始化剩下的单实例bean：finishBeanFactoryInitialization(beanFactory);
 *                  1)  创建业务逻辑组件和切面组件
 *                  2)  后置处理器拦截创建组件的过程
 *                          1)  组件创建完成之后，判断组件是否需要增强，
 *                                  1)  是，切面的通知方法包装成增强器，给业务逻辑对象创建一个代理对象
 * 5)   执行目标方法
 *          1)  代理对象执行目标方法
 *          2)  用CglibAopProxy.intercept()进行拦截
 *                  1)  得到目标方法的拦截器链(增强器包装成拦截器)
 *                  2)  利用链接器链机制，依次进入每个拦截器进行执行
 *          3)  效果：
 *                  正常执行：前置通知--->目标方法--->后置通知--->返回通知
 *                  异常执行：前置通知--->目标方法--->后置通知--->异常通知
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/25 10:33
 * @Desc
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    @Bean("mathCalculator")
    public MathCalculator mathCalculator() {

        return new MathCalculator();
    }

    @Bean("logAspects")
    public LogAspects logAspects() {
        return new LogAspects();
    }

}
