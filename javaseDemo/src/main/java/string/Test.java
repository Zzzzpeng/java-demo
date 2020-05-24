package string;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
//    public static void main(String[] args) {
//        String a = new String("ab"); // a 为一个引用
//        String b = new String("ab"); // b 为另一个引用,对象的内容一样
//        String aa = "ab"; // 放在常量池中
//        String bb = "ab"; // 从常量池中查找
//        if (aa == bb) // true
//            System.out.println("aa==bb");
//        if (a == b) // false，非同一对象
//            System.out.println("a==b");
//        if (a.equals(b)) // true
//            System.out.println("aEQb");
//        if (42 == 42.0) { // true
//            System.out.println("true");
//        }
//        ReentrantLock reentrantLock = new ReentrantLock();
//        reentrantLock.lock();
//        new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
//    }

    public static void main(String[] args) {
        int count = 0;
        retry:
        for (int i=0; i<3; i++) {
            for (int k=0; k<2; k++) {
                for (int j = 0; j < 5; j++) {
                    count++;
                    if (count == 4) {
                        break retry;
                    }
                    System.out.print(count + " ");
                }
            }
        }

    }
}
