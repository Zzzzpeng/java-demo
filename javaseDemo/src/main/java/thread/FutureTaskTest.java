package thread;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureTaskTest {
    static String[] names = {"你","我","他"};
    static ThreadPoolExecutor executorService = new ThreadPoolExecutor(5,5,4,
            TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    static int COUNT_BITS = Integer.SIZE - 3; //29

    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;  //0b001...0 - 1


    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS; //0b11..1000 -8
    private static final int SHUTDOWN   =  0 << COUNT_BITS; //0b0
    private static final int STOP       =  1 << COUNT_BITS; //0b001....0
    private static final int TIDYING    =  2 << COUNT_BITS; //0b010....0
    private static final int TERMINATED =  3 << COUNT_BITS; //0b011....0
//    String[] names
    static void exceptionTest() throws InterruptedException, ExecutionException {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                throw new RuntimeException("xx异常");
//            }
//        });
//        thread.start();
//        Thread.sleep(2000);
//        System.out.println("正常结束");
        ExecutorService executorService = new ThreadPoolExecutor(0, 1,
                2, TimeUnit.SECONDS, new LinkedBlockingDeque(10));

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("执行Callable");
                if (true)
                    throw new RuntimeException("嘻嘻嘻");
                return "zxc";
            }
        });
        Thread.sleep(1000);

        System.out.println(future.get());
        System.out.println("正常结束...");

    }
    static void threadPoolTest() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(0, 1,
                2, TimeUnit.SECONDS, new LinkedBlockingDeque(10));
        for (int i = 0; i < 1; i++) {
            try {
                executorService.execute(() -> {
                        System.out.println(Thread.currentThread().getName());
                        throw new RuntimeException("认个东西");
                });
            } catch (Exception e) {
                System.out.println("出现异常");
            }

        }
        Thread.sleep(3000);
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("看看还能跑吗");
        });
//        executorService.shutdown();
    }

    private static Callable callable = () -> {
        System.out.println("正在获取密码");
        Thread.sleep(1200);
        return "3331";
    };

    static void callableTest() throws Exception {
        List<Future> futureList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            Future<?> submit = executorService.submit(callable);
//            System.out.println(submit.get());
            futureList.add(submit);
        }
        futureList.forEach(item->
        {
            try {
                System.out.println(item.get()
        );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

    static void futureTaskTest() throws Exception {

        List<Future> futureList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            FutureTask<String> futureTask = new FutureTask<>(callable);
            Future<?> submit = executorService.submit(futureTask);
//            System.out.println(submit.get());
            futureList.add(futureTask);
        }
        futureList.forEach(item->
        {
            try {
                System.out.println(item.get()
        );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    static void futureTask() {
        String key = "zep";
        FutureTask_con futureTaskTest = new FutureTask_con();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Connection connection = futureTaskTest.getConnection(key);
                    System.out.println(connection);
                } catch (Exception e) {
                    System.out.println("异常");
                }
            }
        };
    }

    public static void main(String[] args) throws Exception {
//
//        threadPoolTest();
//        callableTest();
          callable();

    }

    public static void callable() {
        List<Future> futureList = new ArrayList<>();
        for (String name : names) {
            Future<String> future = executorService.submit(new Task(name));
            futureList.add(future);
        }
        futureList.forEach(item-> {
            try {
                System.out.println(item.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }


    static class Task implements Callable<String>{
        String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            System.out.println(name+"--开始执行");
            int i = new Random().nextInt(5);
            Thread.sleep(i*1000);
            System.out.println(name+"--执行完毕,时间"+i);
            return name+i ;
        }
    }
}