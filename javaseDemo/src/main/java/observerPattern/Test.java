package observerPattern;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException {
        Mouse mouse = new Mouse();
        mouse.setHandler(new MouseListener());
        mouse.onClick();
        System.out.println("..................");
        mouse.onMoveOver();
    }
}
