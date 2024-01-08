package org.example.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{

    private String dirPath;

    public MyClassLoader(String dirPath) {
        super();
        this.dirPath = dirPath;
    }


    public Class<?> findClass(String name) {
        InputStream is = null;
        try {
            String pathName = name.replaceAll("\\.", "/");
            is = Files.newInputStream(Paths.get(dirPath + "/" + pathName+".class"));
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
