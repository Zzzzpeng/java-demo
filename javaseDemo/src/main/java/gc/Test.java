package gc;

import Inside.Out;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long max = runtime.maxMemory();
        long total = runtime.totalMemory();
        long free = (max - total)>>20;
        System.out.println(max>>20);
        System.out.println(total>>20);
        int capacity = 500000*20;
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(capacity);
        for (int i = 0; i <capacity ; i++) {
            objectObjectHashMap.put(i,new Student());
            Thread.yield();
        }
        objectObjectHashMap.get(Math.random()*10000);
        System.out.println(Runtime.getRuntime().totalMemory()>>20);
        long t2 = System.currentTimeMillis();
        System.out.println("耗时:"+(t2-t1));
    }
}
class Student{
    String name;
    long id;
    int age;
}
