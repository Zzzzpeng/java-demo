package thread;

public class ViseableTest {
    static boolean loop;
    static int age;

    static class Reader extends Thread {

        @Override
        public void run() {
            while (!loop){
                Thread.yield();
                System.out.println("yield");
            }
            System.out.println(age);
        }
    }

    public static void main(String[] args) throws InterruptedException {
         new Reader().start();
        Thread.sleep(1);
        age = 42;
        loop = true;
    }
}
