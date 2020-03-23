package thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FutureTest {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);


//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        List<FutureTask> futureTestList = new ArrayList<>();
//
//        for (int i = 0; i <5 ; i++) {
//            FutureTask futureTask1 = new FutureTask(new RealData1());
//            executorService.execute(futureTask1);
//            futureTestList.add(futureTask1);
//        }
//        for (int i = 0; i <5 ; i++) {
//            FutureTask futureTask1 = new FutureTask(new RealData());
//            executorService.execute(futureTask1);
//            futureTestList.add(futureTask1);
//        }
//
//        for (FutureTask futureTask : futureTestList) {
//            System.out.println(futureTask.get());
//        }
//        System.out.println("thread-main         end");
//        executorService.shutdown();
//
//    }

}

class RealData implements Callable<String> {

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"      生产中...");
            Thread.sleep(500);
        }
        return "经过两秒,响应'嘻嘻嘻'";
    }
}
class RealData1 implements Callable<String> {

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName()+"      生产中...");
            Thread.sleep(500);
        }
        return "经过两秒,响应'嘻嘻嘻'";
    }
}