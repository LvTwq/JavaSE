package com.example.cglib;


import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author 吕茂陈
 * @date 2021/11/02 10:12
 */
@Slf4j
public class SampleClass {

    public static void main(String[] args) {
        /*
        创建一个被代理对象的子类并且拦截所有放入方法调用（包括toString和hashCode），final方法除外，Enhancer也不能对final类进行代理操作
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                log.info("方法执行之前！！！");
                Object result = methodProxy.invokeSuper(o, args);
                log.info("方法执行之后！！");
                return result;
            }
        });
        SampleClass sampleClass = (SampleClass) enhancer.create();
        sampleClass.test();
    }

    /**
     * 这个方法必须是public，不然没法被代理
     */
    public void test() {
        log.info("hello world！！！");
    }

    public String test(String input){
        return "hello world";
    }
}
