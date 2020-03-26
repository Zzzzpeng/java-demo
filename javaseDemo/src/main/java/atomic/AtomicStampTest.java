package atomic;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampTest {



    public static void main(String[] args) throws InterruptedException {
        Integer value = 0;
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(value, 0);
        Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                while (true) {
                    final int timeStamp = atomicStampedReference.getStamp();
                    Integer current = atomicStampedReference.getReference();
                    if (atomicStampedReference.compareAndSet(current, current + 1,
                            timeStamp, timeStamp + 1)){
                        break;
                    }else {
                        System.out.println(atomicStampedReference.getReference());
                    }
                }
            }).start();

        }
    }
}
