package com.example.cglib;

import org.junit.Test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

/**
 * @author 吕茂陈
 * @date 2021/11/02 10:36
 */
public class TestCglib {

    @Test
    public void testFixedValue() {
        Enhancer enhancer = new Enhancer();
        // 设置父类型
        enhancer.setSuperclass(SampleClass.class);
        // FixedValue 用来对所有拦截的方法，返回相同的值，
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "hello cglib";
            }
        });
        // 创建增强对象
        SampleClass proxy = (SampleClass) enhancer.create();
        // test方法、toString方法 都被拦截，输出 Hello cglib
        System.out.println("第一：" + proxy.test(null));
        System.out.println("第二：" + proxy.toString());
        // 这个方法不能被拦截，因为他是 final
        System.out.println("第三：" + proxy.getClass());
        // hashCode()方法需要返回一个Number，但是返回的是一个String，所以抛异常
        System.out.println("第四：" + proxy.hashCode());
    }
}
