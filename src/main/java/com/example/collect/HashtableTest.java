package com.example.collect;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Hashtable;
import java.util.Map;



/**
 * @author 吕茂陈
 */
public class HashtableTest {
    public static void main(String[] args) {
//        Map<A, Object> ht = new HashMap(3);
        Map<A, Object> ht = new Hashtable<>(3);
        ht.put(new A(60000), "java");
        ht.put(new A(87563), "python");
        ht.put(new A(1232), new B());
        System.out.println(ht);
        // 只要两个对象通过equals()方法比较返回true，Hashtable就认为他们是相等的value
        System.out.println("=========测试字符串===========" + ht.containsValue("测试字符串"));
        System.out.println(ht.containsKey(new A(1232)));

        System.out.println("-------------------");
        // 删除最后一个key-value
        Object o = ht.remove(new A(1232));
        System.out.println(o);
        System.out.println(o.getClass());
        System.out.println(ht);
    }


    @EqualsAndHashCode
    @AllArgsConstructor
    static class A {
        int count;
    }


    static class B {
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }
}
