package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VisiableTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(2);
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(map.get("1"));
        }).start();

        new Thread(() -> {
            map.put("1", "123");
            map.put("12", "321");
        }).start();

    }
}
