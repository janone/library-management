package org.example.common;

import org.example.controller.IUserController;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
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
                // 2 编译源码
                compileSourceCode(sourceCode);
                // 3 类加载
//                Class.forName(clazz1.getName());
                // 4 实例化
//                return (T) BeanFactory.getBean(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private static void compileSourceCode(String sourceCode) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> objectsFromStrings = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(sourceCode));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, objectsFromStrings);
        Boolean call = task.call();
        System.out.println(call);
    }

    private static <T> String generateSourceCode(Class<T> clazz) {

        String simpleName = clazz.getSimpleName();
        StringBuilder builder = new StringBuilder("package org.example.common; " +
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


    public static void main(String[] args) {
        String s = generateSourceCode(IUserController.class);
        System.out.println(s);
        compileSourceCode(s);
    }

}
