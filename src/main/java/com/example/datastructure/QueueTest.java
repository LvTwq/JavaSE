package com.example.datastructure;

import java.util.LinkedList;
import java.util.Queue;


public class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        //添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        queue.forEach(System.out::println);
    }
}
