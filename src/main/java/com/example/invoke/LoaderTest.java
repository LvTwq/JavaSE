package com.example.invoke;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LoaderTest {

    static {
        log.info("Tester类的静态初始化块。。。");
    }

    @Test
    public void testStatic() throws ClassNotFoundException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        // 仅将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块。
        cl.loadClass("com.example.invoke.LoaderTest");
        log.info("系统加载Tester类");
        // 将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块
        Class.forName("com.example.invoke.LoaderTest");
    }
}
