package com.example.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author 吕茂陈
 * @description
 * @date 2022/11/15 17:38
 */
@Slf4j
public class StackTest {


    public static void main(String[] args) {
        eat();
        throwEx();
    }


    public static void eat() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            log.info("index: " + i + " ClassName: " + elements[i].getClassName() + ", Method Name : " + elements[i].getMethodName());
        }
        log.info("eat");
    }

    public static void throwEx() {
        throw new RuntimeException("异常堆栈！");
    }


    @Test
    public void test01() {
        try (FileOutputStream fileInputStream = new FileOutputStream(new File("D://", "jstack.0.log"))) {
            JVMUtil.jstack(fileInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
