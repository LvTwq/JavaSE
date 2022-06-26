package com.example.exception;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2021/11/14 14:33
 */
@Slf4j
public class ExceptionTest {

    @Test
    public void test01() {
        try {
            method01();
        } catch (Exception e) {
            // 抛出它
            throw new RuntimeException("主方法发生异常！", e);
        }
        // 不会输出
        log.info("{}", "!!!!!!!!!!!!!");
    }

    private void method01() {
        // 如果上面写了e，也会输出它
        throw new RuntimeException("被调用的方法发生异常！");
    }

    @Test
    public void test02() throws MalformedURLException {
        // 因为这个构造方法里抛出了，所以要在方法签名上也抛出(抛出异常➡我不处理这个异常，谁调用谁处理)
        URL url = new URL("https://www.runoob.com/");
    }

    /**
     * throw关键字是用于方法体内部，用来抛出一个Throwable类型的异常。
     * 如果抛出了检查异常，则还应该在方法头部声明方法可能抛出的异常类型
     *
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        throw new IOException();
    }


    @Test
    public void test04() {
        try {
            log.info("try");
            //异常丢失
            throw new RuntimeException("try");
        } finally {
            log.info("finally");
            // 会覆盖try中的异常（因为一个方法无法出现两个异常）
            throw new RuntimeException("finally");
        }
    }


    @Test
    public void test05() throws Exception {
        Exception e = null;
        try {
            log.info("try");
            throw new RuntimeException("try");
        } catch (Exception ex) {
            e = ex;
        } finally {
            log.info("finally");
            try {
                throw new RuntimeException("finally");
            } catch (Exception ex) {
                if (e != null) {
                    e.addSuppressed(ex);
                } else {
                    e = ex;
                }
            }
        }
        throw e;
    }

    @Test
    public void useresourcewrong() throws Exception {
        TestResource testResource = new TestResource();
        try {
            testResource.read();
        } finally {
            testResource.close();
        }
    }


    @Test
    public void useresourceright() throws Exception {
        try (TestResource testResource = new TestResource()) {
            testResource.read();
        }
    }


    @Test
    public void wrong() {
        try {
            createOrderWrong();
        } catch (Exception ex) {
            log.error("createOrder got error", ex);
        }
        try {
            cancelOrderWrong();
        } catch (Exception ex) {
            log.error("cancelOrder got error", ex);
        }
    }


    private void createOrderWrong() {
        //这里有问题
        throw Exceptions.ORDEREXISTS;
    }

    private void cancelOrderWrong() {
        //这里有问题
        Exceptions.orderExists();
    }
}


class Exceptions {

    /**
     * 这样打印出来的异常堆栈信息会显示方法调用路径
     */
    public static BusinessException ORDEREXISTS = new BusinessException("订单已经存在", 3001);

    public static BusinessException orderExists() {
        return new BusinessException("订单已经存在", 3001);
    }
}


class BusinessException extends RuntimeException {

    public BusinessException(String message, int i) {
        super(message);
    }
}

class TestResource implements AutoCloseable {

    public void read() throws Exception {
        throw new Exception("read error");
    }

    @Override
    public void close() throws Exception {
        throw new Exception("close error");
    }
}
