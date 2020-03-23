package thread;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock lock0 = new ReentrantLock();
    static Lock lock1 = new ReentrantLock();
    static String str0;
    static String str1;

    public void joinTest() throws InterruptedException {
        Thread t0 = new Thread(()-> {System.out.println(0);});
        Thread t1 = new Thread(()-> System.out.println(1));
        Thread t2 = new Thread(()-> System.out.println(2));
        t0.start();
        t0.join();
        t1.start();
        t1.join();
        t2.start();
        t2.join();

    }


    //futureTask状态测试
    public static void futureTaskStateTest(){
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("嘻嘻嘻");
                return "";
            }
        }) ;
        new Thread(futureTask).start();
    }

    public void readWriteLockTest(){
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        readLock.lock();
        readLock.lock();
        readLock.unlock();
        readLock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        futureTaskStateTest();
        int a ;
        test(a = 4);
    }
    static void test(int a){
        System.out.println(a);
        ThreadLocal<HashMap> threadLocal = new ThreadLocal<>();
        threadLocal.get();
    }

}
class User{
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        Thread.interrupted();
    }
}
