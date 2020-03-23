package com.chen.bookmanager.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class PayDescVo {
    int id;
    int price;
    Date date;

    public PayDescVo() {
    }

    public PayDescVo(int price, Date date) {
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    public static void main(String[] args) throws Exception {
//        String a = new String("abc");
//        a.intern();
//        String b = "abc";
//        System.out.println(a == b);
//    }
    public static void main(String[] args) throws Exception {
        System.out.println("hello world");
    }
}
