package log;

import java.util.logging.Level;

public class Test {
    public static void main(String[] args) {
        MyLogger.getLogger().log(Level.WARNING,"危险操作!");
    }
}
