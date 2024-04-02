package com.example.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * @author 吕茂陈
 * @date 2021/11/01 20:11
 */
@Slf4j
public class MyInvocationHandler01 implements InvocationHandler {

    /**
     * 执行动态代理对象的所有方法时，都会被替换成此方法
     *
     * @param proxy  代表动态代理对象
     * @param method 代表正在执行的方法
     * @param args   代表调用目标方法时传入的实参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("=========正在执行的方法：" + method);
        if (args != null) {
            log.info("下面的执行该方法时传入的实参：");
            for (Object val : args) {
                log.info("{}",val);
            }
        } else {
            log.info("调用该方法没有实参！");
        }
        return null;
    }
}

interface Person {
    void walk();

    void sayHello(String name);
}
