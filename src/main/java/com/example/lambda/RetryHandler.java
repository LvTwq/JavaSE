package com.example.lambda;

/**
 * @author 吕茂陈
 * @date 2022/01/11 08:35
 */
@FunctionalInterface
public interface RetryHandler<T> {

    /**
     * 抛出异常时，触发重试机制
     *
     * @return
     * @throws RuntimeException
     */
    T retry() throws RuntimeException;
}
