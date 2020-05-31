//package com.chen.realproject.apo;
//
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class WebExceptionLogger {
//    public WebExceptionLogger(){
//        System.out.println("创建MyLog实例");
//    }
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//
//    @Pointcut("execution(* com.chen.realproject.controller.*.*(..))")
//    public void PointcutDeclaration(){}
//    @Pointcut("execution(* com.chen.realproject.service.impl.*.*(..))")
//    public void PointcutDeclaration1(){}
//
//
//    @AfterThrowing(pointcut = "PointcutDeclaration()",throwing = "e")
//    public void handleThrowing(Exception e){
//        System.out.println("开始记录异常");
//        logger.error("aop开始记录异常");
//        logger.error(e.toString());
//    }
//
//    @Around("PointcutDeclaration1()")
//    public void around(ProceedingJoinPoint proceedingJoinPoint){
//        System.out.println();
//        System.out.println("Aspect执行方法");
//        try {
//            proceedingJoinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }
//
//}