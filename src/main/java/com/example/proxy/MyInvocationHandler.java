package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.Setter;

/**
 * @author 吕茂陈
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 需要被代理的对象
     */
    @Setter
    private Object target;


    /**
     * 执行动态代理对象的所有方法时，都会被替换成执行这个 invoke() 方法
     *
     * @param proxy  代表动态代理对象
     * @param method 代表正在执行的方法
     * @param args   代表调用目标方法时传入的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DogUtil du = new DogUtil();
        // 执行DogUtil对象中的method1方法
        du.method1();
        // 通过反射，以target作为主调来执行method方法，这就回调了target对象的原有方法
        Object result = method.invoke(target, args);
        du.method2();
        return result;
    }
}


