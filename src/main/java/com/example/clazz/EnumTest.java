package com.example.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 吕茂陈
 * @date 2022/04/05 15:54
 */
public class EnumTest {


//    public <T> T convertEnum(Enum<?> convertEnum) {
//
//    }


    public <T> T convertEnum() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<? extends EnumTest> forName = this.getClass();
        Field[] declaredFields = forName.getDeclaredFields();
        for (Field field : declaredFields) {
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            // 获得字段对象类型
            Class<?> enumClass = field.getType();
            if (enumClass.isEnum()) {
                // 破坏私有属性
                field.setAccessible(true);
                // 获取get方法名
                String methodName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                // 获取并调用当前this类对应枚举属性的get方法
                Method getMethod = forName.getDeclaredMethod("get" + methodName);
                // 调用方法获取返回的枚举对象
                Enum oldEnum = (Enum) getMethod.invoke(this);
                // 获取当前枚举的 setDesc 方法
                Method enumSetDesc = enumClass.getDeclaredMethod("setDesc", String.class);
                // 调用set方法并把枚举name值复值为desc值
                enumSetDesc.invoke(oldEnum, oldEnum.name());

            }
        }
        return (T) this;
    }
}
