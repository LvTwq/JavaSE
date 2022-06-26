package com.example.annotation;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class ProcessorTest {

    public static void process(String clazz) throws ClassNotFoundException {
        int passed = 0;
        int failed = 0;

        // 遍历clazz对应的类里面的所有方法
        for (Method method : Class.forName(clazz).getMethods()) {
            // 该方法如果使用@Testable注解修饰
            if (method.isAnnotationPresent(Testable.class)) {
                try {
                    // 调用m方法
                    method.invoke(null);
                    passed++;
                } catch (Exception e) {
                    log.error("方法【{}】运行失败，failed计数器加1", method, e);
                    failed++;
                }
            }
        }
        log.info("共运行了【{}】个方法，其中失败【{}】个，成功【{}】个", failed + passed, failed, passed);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ProcessorTest.process("org.example.annotation.MyTest");
    }
}

