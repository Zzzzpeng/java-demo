package com.chen.bookmanager.redis;

import org.springframework.web.context.ContextLoaderListener;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SomeProblemDemo {
    static Lock lock = new ReentrantLock();

    static void ReSyncronizedShow() {
        ReSyncronized reSyncronized = new ReSyncronized();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (reSyncronized.object) {
                reSyncronized.object.notify();
            }
        }).start();
        reSyncronized.show();
    }

    static void ReenLockInteraptTest() {
        ReSyncronized reSyncronized = new ReSyncronized();
        new Thread(() ->
            reSyncronized.keepLock()
        ).start();
        Thread thread = new Thread(() ->
         reSyncronized.tryGetLock()
        );
        thread.start();
        thread.isInterrupted();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.interrupt();
    }

    public static void main(String[] args) {

        ReenLockInteraptTest();
    }

    static class ReSyncronized {
        Lock lock = new ReentrantLock();
        public Object object = new Object();

        public void keepLock() {
            try {
                lock.lock();
                System.out.println("keepLock成功获得锁");
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("keepLock释放锁");
            }
        }

        public void tryGetLock() {
            try {
                lock.lockInterruptibly();
                System.out.println("lockInterruptibly成功获得锁");
                lock.unlock();
                System.out.println("lockInterruptibly释放锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("被打断");
            }
        }

        public void show() {
            synchronized (object) {
                System.out.println(this + "show获得锁");
                reShow();
            }
            System.out.println("释放show");
        }

        public void reShow() {
            synchronized (object) {
                System.out.println(this + "reShow获得锁");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("释放reShow");
        }
    }
}

