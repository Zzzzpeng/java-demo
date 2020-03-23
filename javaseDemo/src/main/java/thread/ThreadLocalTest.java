package thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(40);

        //错误的例子,SimpleDateFormat不是线程安全的,多个线程同时访问会出问题
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Runnable r = ()-> {
                    try {
                        Date parse = simpleDateFormat.parse("201808" + new Random().nextInt(30));
                        System.out.println(parse);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

            };
            for (int i = 0; i < 200; i++) {
                executorService.execute(r);
            }
            executorService.shutdown();
        }


        {       //threadlocal里面有个基于currentThread的entry,用它来存储线程私有的东西
            ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();
            Runnable r = ()-> {
                    SimpleDateFormat simpleDateFormat = threadLocal.get();
                    if (simpleDateFormat == null) {
                        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                        threadLocal.set(simpleDateFormat);
                    }
                    try {
                        Date parse = simpleDateFormat.parse("201808" + new Random().nextInt(30));
                        System.out.println(parse);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

            };
            for (int i = 0; i < 200; i++) {
                executorService.execute(r);
            }
            executorService.shutdown();
        }
    }
}
