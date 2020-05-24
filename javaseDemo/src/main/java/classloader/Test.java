package classloader;

import string.Shower;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
        show();
    }
    public static void MyClassLoader() throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader();
        myClassLoader.setPath("F:\\Shower.class");
        Class<?> shower = myClassLoader.loadClass("string.Shower");
        System.out.println(shower.getClassLoader());
        Method method = shower.getMethod("show", null);
        method.invoke(shower.newInstance(), null);
    }
    public static void show() throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader();
        myClassLoader.setPath("F:\\MyCla.class");
        Class<?> mycla = myClassLoader.loadClass("java.lang.MyCla");
        System.out.println(mycla.getClassLoader());
        Method method = mycla.getMethod("show", new Class[]{});
        method.invoke(mycla.newInstance(), null);

    }
}
