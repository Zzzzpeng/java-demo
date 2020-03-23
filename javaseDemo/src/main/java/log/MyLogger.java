package log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyLogger {
    public static Logger logger = Logger.getLogger("myLogger");
    static  {
        FileHandler fileHandler= null;
        try {
            fileHandler = new FileHandler("f:/javalogs",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);
    }
    public static Logger getLogger(){
        return logger;
    }
}
