package com.example.lambda;

import java.util.function.Function;

/**
 * 受检异常，转换为运行时异常
 *
 * @author 吕茂陈
 * @date 2022-07-06 14:29
 */
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Throwable> {

    static <T, R, E extends Throwable> Function<T, R> unchecked(ThrowingFunction<T, R, E> f) {
        return new Function<T, R>() {
            @Override
            public R apply(T t) {
                try  {
                    return f.apply(t);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    R apply(T t) throws E;

}
