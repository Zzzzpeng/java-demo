package com.chen.bookmanager.model.foodorder;

import com.chen.bookmanager.model.Student;

import java.io.Serializable;
import java.math.BigDecimal;

public class Menu implements Serializable {


    String id;
    String name = "w";
    BigDecimal price;
    boolean delete;
    Boolean test;
    static ThreadLocal<Object> local = new ThreadLocal<>();
    static ThreadLocal<Object> local1 = new ThreadLocal<>();

    public Menu(){}

    public Menu(String id, String name, BigDecimal price, boolean delete) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.delete = delete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", delete=" + delete +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Object o = local.get();
                if (o == null) {
                    System.out.println("local为空");
                    local.set(new Student("张三"));
                } else {
                    System.out.println("不为空");
                    System.out.println(o.toString());
                }
            }
        };
        Thread t1 = new Thread(runnable);

        t1.start();
        t1.join();

        Thread t2 = new Thread(runnable);
        t2.start();



    }

}


