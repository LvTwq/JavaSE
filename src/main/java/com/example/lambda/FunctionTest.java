package com.example.lambda;

import cn.hutool.json.JSONUtil;
import com.example.oop.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/10 10:44
 */
@Slf4j
public class FunctionTest {

    public static void main(String[] args) {
        System.out.println(groupFunction(Person::getName, Person::getAge));
        System.out.println(groupFunction(Person::getName, t -> t));
        System.out.println(groupFunction(t -> t, Person::getName));
    }


    public static <K, V> Map<K, V> groupFunction(Function<Person, K> keyFun, Function<Person, V> valueFun) {
        return Stream.of(Person.builder().age(1).name("LMC").build(), Person.builder().age(2).name("TWQ").build())
                .collect(Collectors.toMap(keyFun, valueFun, (o1, o2) -> o1));
    }


    @Test
    public void test03() {
        consumerMap("1");
    }

    public void consumerMap(String type) {
        Map<String, Consumer<String>> consumerMap = new HashMap<>();
        consumerMap.put("1", s -> action1(s));
        consumerMap.put("2", this::action2);
        consumerMap.put("3", this::action3);
        consumerMap.get(type).accept("123");

        Map<String, Function<String, ?>> functionMap = new HashMap<>();
        functionMap.put("1", s -> action4(s));
//        functionMap.put("2", this::action5);
//        functionMap.put("3", this::action6);
//        String apply = functionMap.get("1").apply(Person.builder().age(1).name("lmc").build());
//        log.info(apply);
    }

    private String action6(String s) {
        return "action6:" + s;
    }

    private String action5(String s) {
        return "action5:" + s;
    }

    private <T> String action4(T t) {
        return "action4:" + JSONUtil.toJsonStr(t);
    }

    private void action3(String s) {
        log.info("action3:{}", s);
    }

    private void action2(String s) {
        log.info("action3:{}", s);
    }

    private void action1(String s) {
        log.info("action1:{}", s);
    }


    @Test
    public void test02() {
        Function<String, String> function = a -> a + " Jack!";
        Function<String, String> function1 = a -> a + " Bob!";
        String greet = function.andThen(function1).apply("Hello");
        // Hello Jack! Bob!
        System.out.println(greet);


        String greet2 = function.compose(function1).apply("Hello");
        // Hello Bob! Jack!
        System.out.println(greet2);

    }

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






}
