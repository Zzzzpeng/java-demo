package thread;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class poolScheduled {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
//        scheduledExecutorService.scheduleAtFixedRate();
        Runnable cmd = () -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务被执行...."+new Date());
        };
        System.out.println(new Date());
//        scheduledExecutorService.scheduleWithFixedDelay(cmd, 2, 4, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(cmd, 2, 4, TimeUnit.SECONDS);
//        scheduledExecutorService.schedule(cmd, 2, TimeUnit.SECONDS);

    }
}
