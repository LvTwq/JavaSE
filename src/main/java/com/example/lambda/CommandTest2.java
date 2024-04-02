package com.example.lambda;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author 吕茂陈
 */
public class CommandTest2 {
    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] array = {3, -4, 6, 4};
        pa.process(array, (int element) ->
                System.out.println(element * element)
        );
    }


    @Test
    public void test02() {
        Stream<Function<String, String>> functionList = Stream.of(
                getE1(), getE2()
        );

        String s = functionList.map(e -> {
                    try {
                        return e.apply("123");
                    } catch (Exception ex) {
                        return "";
                    }
                })
                .filter(StringUtils::isNotBlank).findFirst().orElse("unk");
        System.out.println(s);
    }


    private Function<String, String> getE2() {
        return s -> {
            return s;
        };
    }


    private Function<String, String> getE1() {
        return t -> {
            return "";
        };
    }


    public static <T> T fisrtNonNull(Supplier<T>... s) {
        return Arrays.stream(s).map(Supplier::get).filter(Objects::nonNull).findFirst().orElse(null);
    }
}
