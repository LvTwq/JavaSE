package com.example.generic;

/**
 * @author 吕茂陈
 */
public class InferenceTest {
    public static void main(String[] args) {
        MyUtil<String> ls = MyUtil.nil();
        MyUtil<String> mu = MyUtil.<String>nil();

        MyUtil.cons(42, MyUtil.nil());
        MyUtil.cons(42, MyUtil.<Integer>nil());
    }
}
