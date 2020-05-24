package thread.checkcode;

public class CheckCodeDemo {

    public static void main(String[] args) throws InterruptedException {
        Object value = new Object();
        Thread subThread = new Thread(() -> {
            synchronized (value) {//需要先获取监视器
                try {
                    System.out.println("运行任务时休眠.....");
                    value.wait();//在监视器上休眠等待
                    System.out.println("重新执行任务.....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        subThread.start();
        Thread.sleep(200);//保证notify调用时subThread已休眠,防止没有休眠就notify()导致无法被唤醒
        new Thread(()->{
            synchronized (value){
                value.notify();
            }
        }).start();
    }

}
