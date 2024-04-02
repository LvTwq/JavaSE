package com.example.collect;

import java.util.HashMap;
import java.util.Map;

public class HashMapErrorTest {
    public static void main(String[] args) {
        Map<String, Object> ht = new HashMap<>();
        ht.get(null);


        Map<Object, Object> of = Map.of();
        HashMap<Object, Object> map = new HashMap<>(of);
        map.get(null);

        // NullPointerException
        of.get(null);
    }
}
