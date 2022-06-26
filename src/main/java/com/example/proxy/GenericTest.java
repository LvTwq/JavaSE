package com.example.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 吕茂陈
 */
public class GenericTest {
    private Map<String, Integer> score;

    public static void main(String[] args) throws Exception {
        // 获取class对象
        Class<GenericTest> clazz = GenericTest.class;
        Field f = clazz.getDeclaredField("score");
        // 使用 getType() 取出类型，只对普通类型的成员变量有效
        Class<?> a = f.getType();
        // 仅输出 java.util.Map
        System.out.println("score的类型是：" + a);
        // 获取成员变量f的泛型类型
        Type type = f.getGenericType();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            // 获取原始类型
            Type rawType = pType.getRawType();
            System.out.println("原始类型是：" + rawType);
            // 获取泛型类型的泛型参数
            Type[] tArgs = pType.getActualTypeArguments();
            System.out.println("泛型信息是：");
            AtomicInteger i = new AtomicInteger(1);
            Arrays.stream(tArgs).forEach(e ->
                    System.out.println("第" + i.getAndIncrement() + "个泛型参数是：" + e));
        } else {
            System.out.println("获取泛型类型出错");
        }
    }
}
