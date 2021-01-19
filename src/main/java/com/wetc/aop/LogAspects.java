package com.wetc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/25 10:56
 * @Desc @Aspect，定义切面类
 */
@Aspect
public class LogAspects {

    // 抽取公共的切入表达式
    @Pointcut("execution(public int com.wetc.aop.MathCalculator.*(..)))")
    public void piontCut() {

    }

    // JoinPoint一定出现在参数表的第一位
    @Before("piontCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(""+joinPoint.getSignature()+"除法运行..@Before...参数列表是:{"+ Arrays.asList(args) +"}");
    }

    @After("piontCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(""+joinPoint.getSignature()+"除法结束...@After...参数列表是:{}");
    }

    @AfterReturning(value = "piontCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(""+joinPoint.getSignature()+"除法正常返回...@AfterReturning...运行结果是:{"+result+"}");
    }

    @AfterThrowing(value = "piontCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(""+joinPoint.getSignature()+"除法异常...@AfterThrowing...运行结果是:{"+exception+"}");
    }
}
