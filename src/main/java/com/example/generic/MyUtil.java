package com.example.generic;

/**
 * @author 吕茂陈
 */
public class MyUtil<E> {
    public static <Z> MyUtil<Z> nil() {
        return null;
    }

    @Deprecated
    public static <Z> MyUtil<Z> cons(Z head, MyUtil tail) {
        return null;
    }

    E head() {
        return null;
    }
}
