package com.example.invoke;

import javax.tools.*;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author 吕茂陈
 * @create 2022-06-28 15:32
 */
public class CompilerTest {

    public static Class<?> compile(String name, String content) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StrSrcJavaObject srcObject = new StrSrcJavaObject(name, content);
        Iterable<? extends JavaFileObject> fileObjects = Collections.singletonList(srcObject);
        String flag = "-d";
        String outDir = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
        Iterable<String> options = Arrays.asList(flag, outDir);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, fileObjects);
        boolean result = task.call();
        if (result == true) {
            System.out.println("Compile it successfully.");
            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            /*ClassLoader loader = CompileTest.class.getClassLoader();
            Class<?> cls;
            try
            {
                cls = loader.loadClass(name);
                return cls;
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }*/
        }
        return null;
    }


    public static void main(String[] args) {
        Class<?> cls = compile("com.example.demo.coding.Test1996",
                "package com.example.demo.coding;  public class Test1996{ public static void main(String[] args){System.out.println(\"字符串中的方法执行了\");} }");
        System.out.println("类名=" + cls.getCanonicalName());

        Method method = null;
        try {
            method = cls.getMethod("main", String[].class);
            method.invoke(null, new Object[]{new String[]{}});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("方法名=" + method.getName());
    }


    private static class StrSrcJavaObject extends SimpleJavaFileObject {
        private String content;

        public StrSrcJavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }
}
