package com.example.optional;

import java.util.Optional;

/**
 * @author 吕茂陈
 */
public class whenCreateEmptyOptional_thenNull {
    public static void main(String[] args) {
        Optional<Object> empty = Optional.empty();
        Optional<Optional<Object>> empty1 = Optional.ofNullable(empty);
//        System.out.println(empty1);

        empty.ifPresent((value) -> {
            System.out.println(value);
        });

    }

}
