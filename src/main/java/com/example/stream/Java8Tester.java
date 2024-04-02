package com.example.stream;

import com.example.oop.Dog;
import com.example.oop.Person;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 吕茂陈
 */
public class Java8Tester {

    @Test
    public void test() {
        List<Person> lists = new ArrayList<>();
        Integer integer = lists.stream().findFirst().map(Person::getAge).orElse(null);
        System.out.println(integer);
    }

    @Test
    public void test02() {
        IntStream is = IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .build();

        // 以下调用的聚集方法的代码每次只能执行一行
        System.out.println("is所有元素的最大值：" + is.max().getAsInt());
        System.out.println("is所有元素的最小值：" + is.min().getAsInt());
        System.out.println("is所有元素的总和：" + is.sum());
        System.out.println("is所有元素的总数：" + is.count());
        System.out.println("is所有元素的平均值：" + is.average());
        System.out.println("is所有元素的平方是否大于20：" + is.allMatch(ele -> ele * ele > 20));
        System.out.println("is是否包含任何元素的平方大于20：" + is.anyMatch(ele -> ele * ele > 20));

        // 将is映射成一个新的Stream，新stream的每个元素是原来的2倍+1
        IntStream newIs = is.map(ele -> ele * 2 + 1);
        newIs.forEach(System.out::println);

    }



    @Test
    public void test03() {
        // map 映射每个元素到对应的结果
        List<Integer> n = Arrays.asList(3, 2, 1, 5, 7, 9);
        // 获取对应的平方数
        n.stream().map(i -> i * i).distinct().collect(Collectors.toList()).forEach(System.out::println);

        // filter 通过设置的条件过滤出元素
        List<String> strings = Arrays.asList("asd", " ", "dfg", "", "lll");
        // 获取空字符串的数量
        long count = strings.stream().filter(s -> strings.isEmpty()).count();
        System.out.println(count);
    }

    /**
     * Reduce(规约)
     * 这是一个 最终操作 ，允许通过指定的函数来将stream中的多个元素规约为一个元素，规约后的结果是通过Optional 接口表示的
     */
    @Test
    public void test04() {
        List<String> names = Lists.newArrayList("Java", "python", "php", "c++", "JavaScript");
        Optional<String> reduce = names.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduce.ifPresent(System.out::println);

        // 字符串拼接
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
        // 求最小值
        Double min = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(min);
        //求和，起始值为10
        Integer sum = Stream.of(1, 2, 3, 4).reduce(10, Integer::sum);
        System.out.println(sum);
        // 没有起始值，返回值是Optional
        Integer sum1 = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sum1);

        String concat1 = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
        System.out.println(concat1);
    }

    @Test
    public void test05() {
        List<String> names = Lists.newArrayList("Java", "python", "php", "c++", "JavaScript");
        long java = names.stream().filter(ele -> ele.contains("Java")).count();
        System.out.println();

        names.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2).ifPresent(System.out::println);
    }

    @Test
    public void test06() {
        new Random().ints().limit(10).forEach(System.out::println);
    }


    @Test
    public void test07() {

        String[] split = StringUtils.split("安徽省-合肥市-瑶海区", "-");
        System.out.println(split[split.length - 1]);

    }

    @Test
    public void test08() {
        // 这个 person 里的 Dog 为null，在map里直接get，会进 orElse
//        Person person = Person.builder().dog(null).build();
        Person person = Person.builder().dog(Dog.builder().name(null).build()).build();
        String s = Optional.ofNullable(person).map(Person::getDog).map(Dog::getName).orElse("11111");
        System.out.println(s);
    }



    @Test
    public void test09() {
        List<String> imageTypes = List.of("JPG", "JPEG", "GIF", "PNG", "BMP", "PCX", "TGA", "PSD", "TIFF");
        System.out.println(StringUtils.containsIgnoreCase("微信扫码关注，好书还会有的.jpg", "JPG"));
        System.out.println(imageTypes.stream().noneMatch(e -> StringUtils.containsIgnoreCase("微信扫码关注，好书还会有的.jpg", e)));
    }
}
