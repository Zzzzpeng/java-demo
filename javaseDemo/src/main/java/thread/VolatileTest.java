package thread;

public class VolatileTest  {


    static class Reader extends Thread {
         volatile int[] nums = {1, 2};
         boolean goon = true;
//         volatile int age = 3;
        void update(){
            System.out.println("update");
            System.out.println(System.currentTimeMillis());
            goon = false;
            nums[0] = 0;
        }
        @Override
        public void run() {
            int i = 0;
            while (nums[0] == 1 || pt()) {
                i+=1;
            }
            System.out.println(i);
        }
        public boolean pt(){
//            System.out.println(System.currentTimeMillis());
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Reader reader = new Reader();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> reader.run()).start();
        }
//        reader.start();
        Thread.sleep(800);
        new Thread(() -> reader.update()).start();
    }


//    boolean stop = false;
	 boolean stop = false;

//    public static void main(String[] args) throws Exception{
//        VolatileTest v = new VolatileTest();
//        Thread ta = new Thread(()->v.execute());
//        ta.start();
//        Thread.sleep(20);
//        Thread tb = new Thread(()->v.shutdown());
//        tb.start();
//    }

    public void execute(){
        while(!stop){
            String a = "a";
//			System.out.print("");
        }
    }
    public void shutdown(){
        System.out.println("do stop");
        stop = true;
    }

}
