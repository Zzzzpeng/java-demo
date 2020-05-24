package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MyClassLoader extends ClassLoader {
    Set<String> clzs = new HashSet<>();
    String path;
    {
        clzs.add("string.Shower");
        clzs.add("java.lang.MyCla");
    }
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if(clzs.contains(name))
            return findClass(name);
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadByteDate(path);
        if(bytes == null)
            return null;
        return defineClass(name, bytes, 0, bytes.length);
    }
    byte[] loadByteDate(String path) {
        File file = new File(path);
        if (file.exists()){
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
            try {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = in.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            return out.toByteArray();
        }else{
            return null;
        }
    }


}
