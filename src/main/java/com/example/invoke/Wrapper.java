package com.example.invoke;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/5/31 15:59
 */
public class Wrapper<T> {

    public static void main(String[] args) {

        Type type1 = getGenericRuntimeType(new Wrapper<List<String>>());
        System.out.println(type1);
        // 创建了一个Wrapper的匿名类
        Type type2 = getGenericRuntimeType(new Wrapper<List<String>>() {});
        System.out.println(type2);
    }


    public static <T> Type getGenericRuntimeType(Wrapper<T> wrapper) {
        Type type = wrapper.getClass().getGenericSuperclass();
        if (type == null) {
            return null;
        }

        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            return types[0];
        }
        return null;
    }
}
