package thread;

public class InterruptedTest {
    static void interruptedTest() {
        Thread currentThread = Thread.currentThread();
        currentThread.interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
    }

    static void isInterruptedTest() {
        Thread currentThread = Thread.currentThread();
        currentThread.interrupt();
        System.out.println(currentThread.isInterrupted());
        System.out.println(currentThread.isInterrupted());
    }

    //睡眠时被中断
    static void terruptTest_sleep() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1110);
            } catch (InterruptedException e) {
                System.err.println("被中断");
            }
        });
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

    //获取锁时被中断
    static void terruptTest_block() throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                for (; ; ) {

                }
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                synchronized (o) {
                    for (; ; ) {
                        System.out.println(2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread.sleep(50);
        t2.start();
        Thread.sleep(50);
//        t2.interrupt();
//        Thread.sleep(100);
        System.out.println(t2.getState());

    }

    //获取锁时被中断
    void terruptTest_wait() throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    System.err.println("wait时被断");
                }
                System.out.println(Thread.currentThread().getState());
            }
        });

        t1.start();
        Thread.sleep(50);
        System.out.println(t1.getState());
    }

    public static void main(String[] args) throws InterruptedException {
        new InterruptedTest().terruptTest_wait();
//        terruptTest_block();
    }
}
