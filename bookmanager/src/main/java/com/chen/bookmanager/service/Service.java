package com.chen.bookmanager.service;

@org.springframework.stereotype.Service
public class Service {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Runnable runnable  = new Task();
        ((Task) runnable).setObject(object);
        Thread t1 = new Thread(runnable);
        t1.start();
        Thread.sleep(2000);
        System.out.println("休眠完毕");
        ((Task) runnable).setKey("ni ");
        synchronized (object) {
            object.notify();
        }
    }
}

class Task implements Runnable {
    private  Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void run() {
        synchronized (object) {
            try {
                object.wait();
                System.out.println(key);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }
}