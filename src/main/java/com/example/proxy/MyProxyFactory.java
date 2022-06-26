package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理工厂类
 *
 * @author 吕茂陈
 */
public class MyProxyFactory {

    /**
     * 为指定的target生成动态代理对象，这个动态代理对象与target实现了相同的接口，所以具有相同的public方法
     *      从这个意义上，动态代理对象可以当成target对象使用，当程序调用动态代理对象的指定方法时，
     *      实际上将变成执行 MyInvocationHandler 对象的 invoke 方法，
     *
     * @param target
     * @return 动态代理对象
     */
    public static Object getProxy(Object target) {
//        MyInvocationHandler handler = new MyInvocationHandler();
        // 为 MyInvocationHandler 设置 target 对象
//        handler.setTarget(target);
        // 创建并返回一个动态代理
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
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
        });
    }
}
