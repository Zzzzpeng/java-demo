package thread;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTask_con {
    private  ConcurrentHashMap<String, FutureTask<Connection>> concurrentHashMap = new ConcurrentHashMap<>();
    Callable<Connection> callable = new Callable<Connection>() {
        @Override
        public Connection call() throws Exception {
            return createConnection();
        }
    };
    public Connection getConnection(String key) throws ExecutionException, InterruptedException {
        FutureTask<Connection> futureTask = concurrentHashMap.get(key);
        //创建一个任务,放进容器;如果容器中该位置之前没有任务,才执行run(),否则每一个新的任务都有独立的run(),
        if (futureTask != null) {
            return futureTask.get();
        }

        futureTask = new FutureTask<>(callable);
        //看看里面有没有
        FutureTask<Connection> oldTask = concurrentHashMap.putIfAbsent(key, futureTask);
        if(oldTask == null){
            //没有,成功放入,执行futureTask
            futureTask.run();
        }else {
            //里面已经有了,直接拿oldTask的结果
           futureTask = oldTask;
        }
        return futureTask.get();


    }

    public Connection createConnection() {
        Connection connection = new Connection();
        System.out.println(connection);
        return connection;
    }


}

class Connection{
    public Connection(){
        System.out.println("产生新连接实例");
    }
}
