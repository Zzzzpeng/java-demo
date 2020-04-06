package jvm;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
//        loadClass();
        loadClass1();
    }
    public static Object loadClass() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException, InvocationTargetException {

        File file = new File("E:\\");
        URL url = file.toURL();
        URL[] urls = new URL[]{url};
        // Create a new class loader with the directory
        ClassLoader cl = new URLClassLoader(urls);
        // Load in the class; Test2.class should be located in
        // the directory file:/D：\test\zy\
        Class cls = cl.loadClass("ClassLoaderTest");
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
            declaredMethod.invoke(cls.newInstance(), null);
        }
        return null;
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        classLoader.loadClass("E:\\ClassLoaderTest.class");
    }


    public static Object loadClass1() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException, InvocationTargetException {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader.getParent());
        Class<?> cls = classLoader.loadClass("jvm.ClassLoaderTest");
        System.out.println(cls);
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        return null;
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        classLoader.loadClass("E:\\ClassLoaderTest.class");
    }
}
