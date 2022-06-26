package com.example.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author 吕茂陈
 */
public class GenericMethodTest {
    static <T> void fromArrayToCollection(T[] a, Collection<T> c){
        for (T o :
                a) {
            c.add(o);
        }
    }

    public static void main(String[] args) {
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<>();
        fromArrayToCollection(oa,co);
        
        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<>();
        fromArrayToCollection(sa,cs);
    }
}
