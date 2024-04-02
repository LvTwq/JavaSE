package com.example.stream;

import com.example.oop.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 吕茂陈
 */
@Slf4j
public class StreamTest {

    @Test
    public void test() {
        List<Person> lists = new ArrayList<>();
        Integer integer = lists.stream().findFirst().map(Person::getAge).orElse(null);
        log.info("{}", integer);
    }

    @Test
    public void test02() {
        IntStream is = IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .build();

        // 以下调用的聚集方法的代码每次只能执行一行
        log.info("is所有元素的最大值：" + is.max().getAsInt());
        log.info("is所有元素的最小值：" + is.min().getAsInt());
        log.info("is所有元素的总和：" + is.sum());
        log.info("is所有元素的总数：" + is.count());
        log.info("is所有元素的平均值：" + is.average());
        log.info("is所有元素的平方是否大于20：" + is.allMatch(ele -> ele * ele > 20));
        log.info("is是否包含任何元素的平方大于20：" + is.anyMatch(ele -> ele * ele > 20));

        // 将is映射成一个新的Stream，新stream的每个元素是原来的2倍+1
        IntStream newIs = is.map(ele -> ele * 2 + 1);
        newIs.forEach(value -> log.info("{}", value));

    }


    @Test
    public void test03() {
        // map 映射每个元素到对应的结果
        List<Integer> n = Arrays.asList(3, 2, 1, 5, 7, 9);
        // 获取对应的平方数
        n.stream().map(i -> i * i).distinct().collect(Collectors.toList()).forEach(value -> log.info("{}", value));

        // filter 通过设置的条件过滤出元素
        List<String> strings = Arrays.asList("asd", " ", "dfg", "", "lll");
        // 获取空字符串的数量
        long count = strings.stream().filter(s -> strings.isEmpty()).count();
        log.info("{}", count);
    }

    /**
     * Reduce(规约)
     * 这是一个 最终操作 ，允许通过指定的函数来将stream中的多个元素规约为一个元素，规约后的结果是通过Optional 接口表示的
     */
    @Test
    public void test04() {
        List<String> names = Lists.newArrayList("Java", "python", "php", "c++", "JavaScript");
        Optional<String> reduce = names.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduce.ifPresent(value -> log.info("{}", value));

        // 字符串拼接
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        log.info(concat);
        // 求最小值
        Double min = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        log.info("{}", min);
        //求和，起始值为10
        Integer sum = Stream.of(1, 2, 3, 4).reduce(10, Integer::sum);
        log.info("{}", sum);
        // 没有起始值，返回值是Optional
        Integer sum1 = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        log.info("{}", sum1);

        String concat1 = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
        log.info(concat1);
    }

    @Test
    public void test05() {
        List<String> names = Lists.newArrayList("Java", "python", "php", "c++", "JavaScript");
        long l = names.stream().filter(ele -> ele.contains("Java")).count();
        log.info("{}", l);

        names.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2).ifPresent(value -> log.info("{}", value));
    }

    @Test
    public void test06() {
        new Random().ints().limit(10).forEach(value -> log.info("{}", value));
    }


    @Test
    public void test07() {
        Stream.of(1, 2, 3, 4, 5, 6, 7).filter(i -> i % 2 == 0).filter(i -> i > 3).toArray();
    }





    @Test
    public void test08() {
        Stream.of(new Person()).collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getAge))), ArrayList::new));

    }


    @Test
    public void tet09() {
        //将map转换为新map
        Map<String, String> original = Map.of();
        Map<String, Person> copy = original.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> Person.builder().name(e.getValue()).build()));
    }


    @Test
    public void test10() {
        List<Person> list = List.of(Person.builder().name(null).build());
        List<String> collect = list.stream().map(Person::getName).collect(Collectors.toList());

        List<String> strings = List.of("1");
        collect.stream().filter(o -> StringUtils.isNotBlank(o) && strings.contains(o)).
                forEach(e -> System.out.println(e));
    }


    @Test
    public void test11() {
        List<Person> collect = Stream.of(Person.builder().name("lmc").build(), Person.builder().name("lmc").build())
                .filter(distinctByKey(Person::getName))
                .collect(Collectors.toList());
        System.out.println(collect);
    }


    /**
     * 按照对象的某个属性，对list进行去重
     */
    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> objects = ConcurrentHashMap.newKeySet();
        return t -> objects.add(keyExtractor.apply(t));
    }

}
