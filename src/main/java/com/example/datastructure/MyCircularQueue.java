package com.example.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * <p>
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * <p>
 * 你的实现应该支持如下操作：
 * <p>
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/kzlb5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author 吕茂陈
 */
public class MyCircularQueue {

    private int[] data;

    private int startIndex;

    private int endIndex;


    public MyCircularQueue(int k) {
        data = new int[k + 1];
        startIndex = 0;
        endIndex = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        } else {
            data[endIndex] = value;
            endIndex = (endIndex + 1) % data.length;
            return true;
        }
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        } else {
            startIndex = (startIndex + 1) % data.length;
            return true;
        }
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[startIndex];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        int i = (endIndex - 1 + data.length) % data.length;
        return data[i];
    }

    public boolean isEmpty() {
        return startIndex == endIndex;
    }

    public boolean isFull() {
        return (endIndex + 1) % data.length == startIndex;
    }

    public static void main(String[] args) {
//        System.out.println(isAnagram("a", "ab"));

        int[] a = {5, 1, 2};
        a[1] = 0;
        a[12] = 0;
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static boolean isAnagram(String s, String t) {
        // 较长的字符串放到前一个字符串中
        if (s.length() < t.length()) {
            return isAnagram(t, s);
        }
        // key:value = 字符：该字符出现的次数
        Map<Character, Integer> sMap = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        // 遍历较长字符串sMap的key，key如果不在tMap中，或者次数 != 该key在magMap的value
        for (Character c : sMap.keySet()) {
            if (!tMap.containsKey(c) || !sMap.get(c).equals(tMap.getOrDefault(c, 0))) {
                return false;
            }
        }
        for (Map.Entry<Character, Integer> entry : sMap.entrySet()) {

        }
        return true;
    }
}


