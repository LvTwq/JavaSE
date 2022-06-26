package com.example.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author 吕茂陈
 * @date 2021/11/01 20:23
 */
public class ProxyTest {

    public static void main(String[] args) {
        // 创建MyInvocationHandler
        MyInvocationHandler01 handler = new MyInvocationHandler01();
        /* 使用指定的MyInvocationHandler来生成一个动态代理对象
        此时的person是一个动态代理对象，当执行动态代理对象里的方法时，实际上会替换成调用InvocationHandler对象的invoke方法
         */
        Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, handler);
        person.walk();
        person.sayHello("孙悟空");
    }


    @Test
    public void testProxy() throws Exception {
        // 创建一个原始的GunDog对象，作为target
        Dog target = new GunDog();
        // 以指定的target来创建动态代理对象（包含目标对象的全部方法）
        Dog dogTarget = (Dog) MyProxyFactory.getProxy(target);
        // 执行此方法之前，会先执行DogUtil的method1()，再执行target的info()和run()，最后执行DogUtil的method2()
        dogTarget.info();
        dogTarget.run();
    }
}
