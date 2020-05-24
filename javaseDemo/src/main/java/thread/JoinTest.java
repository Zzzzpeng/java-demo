package thread;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread tb = new Thread(() -> {
            try {
                System.out.println("thread b execute..........");
                Thread.sleep(20000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("thread b exit..........");
        });
        tb.setName("tb");
        tb.start();

//        Thread ta = new Thread(() -> {
//            try {
//                System.out.println("thread a execute..........");
//                tb.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("thread a exit..........");
//        });
//        ta.setName("ta");
//        ta.start();

//        tb.join();

        System.out.println("main thread exit");
    }
}
