package com.example.lambda;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/01/11 08:39
 */
@Slf4j
public class Main {

    /**
     * workHandler 返回值为false时，处罚重试
     *
     * @param workHandler
     * @param retryTimes
     * @param <T>
     * @return
     */
    public <T> T retryWhenException(RetryHandler<T> workHandler, int retryTimes) {
        if (retryTimes < 1 || workHandler == null) {
            return null;
        }
        for (int i = 0; i < retryTimes; i++) {
            try {
                return workHandler.retry();
            } catch (Exception e) {
                log.error("重试业务处理异常，当前执行次数：【{}】", i + 1, e);
            }
        }
        return null;
    }


    @Test
    public void test01() {
        int i = retryWhenException(this::compute, 2);
        System.out.println(i);
    }

    @Test
    public void test02() {
        boolean is = retryWhenException(() -> compare(1), 2);
        System.out.println(is);
    }

    private boolean compare(int i) {
        return i > 2;
    }

    private int compute() {
        return 1 / 0;
    }
}
