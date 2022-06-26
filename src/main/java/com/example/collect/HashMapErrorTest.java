package com.example.collect;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapErrorTest {
    public static void main(String[] args) {
        Map<A, Object> ht = new HashMap<>();
        ht.put(new A(60000), "疯狂Java讲义");
        ht.put(new A(87563), "java 实战");
        Iterator<A> it = ht.keySet().iterator();
        A first = it.next();
        first.count = 87563;
        System.out.println(ht);
        ht.remove(new A(87563));
        System.out.println(ht);
        System.out.println(new A(87563));
        System.out.println(new A(60000));
    }
}
