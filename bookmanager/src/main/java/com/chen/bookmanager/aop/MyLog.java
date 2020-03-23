package com.chen.bookmanager.aop;

import com.chen.bookmanager.model.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Aspect
@Component
public class MyLog {

    //第一个*:匹配所有返回值
    //(..)匹配所有参数列表(含午餐)
    @Pointcut("execution(* com.chen.bookmanager.service.*.*(..))")
    public void PointcutDeclaration(){}

//    @Before("PointcutDeclaration()")
//    public void before(JoinPoint joinPoint){
//        System.out.println("执行方法: "+joinPoint.getSignature().getName());
//        System.out.println("参数列表: "+Arrays.toString(joinPoint.getArgs()));
//    }
//
//    @After("PointcutDeclaration()")
//    public void after(JoinPoint joinPoint){
//        System.out.println("方法结束前: "+joinPoint.getSignature().getName());
//        System.out.println("参数列表: "+Arrays.toString(joinPoint.getArgs()));
//    }

    @Around("PointcutDeclaration()")
    public Object surround(ProceedingJoinPoint joinPoint){
        Object res = null;
        try {
            System.out.println("执行方法: "+joinPoint.getSignature().getName());
            System.out.println("参数列表: "+Arrays.toString(joinPoint.getArgs()));
             res = joinPoint.proceed(joinPoint.getArgs());
            System.out.println("返回值:"+res);
            System.out.println("方法结束前: "+joinPoint.getSignature().getName());
            System.out.println("参数列表: "+Arrays.toString(joinPoint.getArgs()));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        finally {
            return res;
        }

    }
    static String returnTest(){
        System.out.println("return");
        return "res";
    }
    static void finallyTest(){
        System.out.println("final");
    }
    static String getStr(){
        Student student = new Student();
        try {
            return returnTest();
        } finally {
            finallyTest();
        }
    }
    static ExecutorService executorService = Executors.newFixedThreadPool(1);

    static void open() throws InterruptedException {
//        Thread.sleep(500);
        System.out.println("打开连接");
    }
    static void close() throws InterruptedException {
//        Thread.sleep(500);
        System.out.println("关闭连接");
    }
    static void loop() throws InterruptedException {
        try {
            open();
            throw new RuntimeException();
        } catch (RuntimeException e) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        loop();
                    } catch (Exception e1) {
                        throw new RuntimeException(e1);
                    }
                }
            });
        } finally {
            close();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        loop();
    }
}
