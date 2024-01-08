package org.example.common;

import org.example.controller.IUserController;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.Arrays;

public final class GenerateUtil {

    public static <T> T generate(Class<T> clazz) {

        /*
        1 拼接源码
        2 编译源码
        3 类加载
        4 实例化
         */
        
        if (clazz.isInterface()) {
            try {
                // 1 拼接源码
                String sourceCode = generateSourceCode(clazz);
                // sourcecode写出到文件
                String path = writeFile(clazz, sourceCode);
                // 2 编译源码
                compileSourceCode(path);
//                 3 类加载
                return loadGenClass(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("client can only be an interface");
        }

        return null;
    }

    private static String parent;

    static {
        String osName = System.getProperty("os.name");
        parent = GenerateUtil.class.getClassLoader().getResource("").getPath()
                + "org/example/controller";
        if (osName.contains("Windows")) {
            parent = parent.substring(1);
        }
    }


    private static <T> T loadGenClass(Class<T> clazz) throws MalformedURLException {

        // 指定类路径
        String classPath = parent;
        // 解析路径，获取文件 URL 数组
        URL[] urls = new URL[1];
        urls[0] = new URL("file://"+parent);
//        System.out.println(urls[0]);
        // 创建自定义类加载器
        URLClassLoader classLoader = new URLClassLoader(urls);
        // 加载指定类
        try {
            Class<?> newClazz = classLoader.loadClass("org.example.controller." + clazz.getSimpleName().substring(1) + "Impl");
            // 创建类的实例并调用方法
            Object instance = newClazz.getDeclaredConstructor().newInstance();
            System.out.println("load success"+instance);

            return (T) instance;
            // 使用自定义类加载器加载的类的方法
            // ...
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> String writeFile(Class<T> clazz, String sourceCode) {
        try {
            String path = parent +"/"+ clazz.getSimpleName().substring(1) + "Impl.java";
            File dir = new File(parent);
            if(!dir.exists()){
                Files.createDirectory(dir.toPath());
            }
            System.out.println(path);
            java.io.File file = new java.io.File(path);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(sourceCode);
            fileWriter.close();
            return path;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static void compileSourceCode(String filePath) {

//        System.out.println("the compile file path:"+filePath);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> objectsFromStrings = fileManager.getJavaFileObjects(filePath);

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, objectsFromStrings);
        Boolean call = task.call();
//        System.out.println(call);
    }

    private static <T> String generateSourceCode(Class<T> clazz) {

        String simpleName = clazz.getSimpleName();
        StringBuilder builder = new StringBuilder("package org.example.controller; " +
                "public class " + simpleName.substring(1) + "Impl implements " + clazz.getName() + " { ");

        for (Method method : clazz.getMethods()) {
            builder.append("public " +
                            method.getGenericReturnType().getTypeName() + " " + method.getName()
                    + "(");
            for (int i = 0; i < method.getParameterCount(); i++) {
                builder.append(method.getParameterTypes()[i].getName() + " " + method.getParameters()[i].getName());
                if (i!= method.getParameterCount() - 1) {
                    builder.append(", ");
                }
            }
            builder.append(") {");
            builder.append("return null;");
            builder.append("}");
        }

        builder.append("}");
        return builder.toString();

    }


    public static void main(String[] args) throws MalformedURLException {
        String s = generateSourceCode(IUserController.class);
        System.out.println("generate ok");
        System.out.println(s);
        String s1 = writeFile(IUserController.class, s);
        System.out.println("write source code ok");
        compileSourceCode(s1);
        System.out.println("compile ok");
        loadGenClass(IUserController.class);
        System.out.println("loadClass ok");
    }

}
