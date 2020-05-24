package java.lang;

public class Mycla {
    public void show() {
        System.out.println(1);
        ThreadLocal.ThreadLocalMap  t = new Thread().threadLocals;

    }
}
