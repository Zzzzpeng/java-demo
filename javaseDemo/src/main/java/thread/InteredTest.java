package thread;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InteredTest {
    static class Task implements Runnable {
        private int num;
        static Map<Integer, Thread> threadMap = new ConcurrentHashMap<>();

        public static Map<Integer, Thread> getThreadMap() {
            return threadMap;
        }

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            threadMap.put(num, Thread.currentThread());
//            while (true){
//                if(Thread.currentThread().isInterrupted()){
//                    throw new RuntimeException(Thread.currentThread().hashCode()+"  丢个异常");
//                }
//                    int count = 0;
//                System.out.println(Thread.currentThread().hashCode()+"执行爬虫任务"+num);
//                Thread.yield();
//            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.execute(new Task(1));
//        executorService.execute(new Task(2));
//        try {
//            Thread.sleep(600);
//            Thread thread = Task.getThreadMap().get(1);
//            if (thread != null) {
//                System.out.println("执行打断");
//                thread.interrupt();
//                System.out.println("打断后:" + thread.isInterrupted());
//            } else {
//                throw new RuntimeException("娶不到!");
//            }
//        } catch (InterruptedException e) {
//            System.out.println("main异常");
//        }
//        executorService.execute(new Task(3));


        WeakReference<Student> weakReference = new WeakReference(new Student("chen"));

        System.gc();
        Thread.sleep(2000);
        System.out.println(weakReference.get());
    }

}
class Student{
    String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}