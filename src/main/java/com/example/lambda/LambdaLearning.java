package com.example.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author 吕茂陈
 */
@Slf4j
public class LambdaLearning {

    Supplier<String> stringSupplier = new Supplier<String>() {
        @Override
        public String get() {
            return "OK";
        }
    };

    Predicate<Integer> positiveNumber = new Predicate<Integer>() {
        @Override
        public boolean test(Integer i) {
            return i > 0;
        }
    };


    Consumer<String> println = new Consumer<String>() {
        @Override
        public void accept(String x) {
            System.out.println(x);
        }
    };

    BinaryOperator<Integer> add = new BinaryOperator<Integer>() {
        @Override
        public Integer apply(Integer a, Integer b) {
            return Integer.sum(a, b);
        }
    };


    @Test
    public void test01() {
        Optional.ofNullable(null).orElseThrow(() -> new RuntimeException("异常"));

    }


}
