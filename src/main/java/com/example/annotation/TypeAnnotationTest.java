package com.example.annotation;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * @author 吕茂陈
 */
@NotNull
public class TypeAnnotationTest implements @NotNull Serializable {
    public static void main(@NotNull String[] args) throws @NotNull FileNotFoundException {
        Object obj = "fkjava.org";
        String str = (@NotNull String) obj;
        JFrame win = new @NotNull JFrame("java");
    }

    public void foo(List<@NotNull String> info){}
}


@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE_USE)
@interface NotNull {
}
