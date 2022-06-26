package com.example.collect;

import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 吕茂陈
 */
public class TreeSetTest {

    @Test
    public void testComparable() {
        TreeSet<M> ts = new TreeSet();
        ts.add(new M(5));
        ts.add(new M(-3));
        ts.add(new M(-9));
        System.out.println(ts);
    }

    @Test
    public void testComparator() {
        TreeSet<M1> ts = new TreeSet(new M1(5));
        ts.add(new M1(5));
        ts.add(new M1(-3));
        ts.add(new M1(-9));
//        ts.add(new M(-9));
        System.out.println(ts);
    }

}


@AllArgsConstructor
@ToString
@EqualsAndHashCode
class M implements Comparable<M> {
    int age;

    /**
     * Comparable是排序接口；若一个类实现了Comparable接口，就意味着“该类支持排序”，相当于“内部比较器”
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(M o) {
        return Integer.compare(age, o.age);
    }

}


@AllArgsConstructor
@ToString
@EqualsAndHashCode
class M1 implements Comparator<M> {
    int age;

    /**
     * Comparator 是比较器接口，我们若需要控制某个类的次序，而该类本身不支持排序(即没有实现Comparable接口)；那么，我们可以建立一个“该类的比较器”来进行排序
     * 相当于“外部比较器”
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(M o1, M o2) {
        return Integer.compare(o1.age, o2.age);
    }

}
