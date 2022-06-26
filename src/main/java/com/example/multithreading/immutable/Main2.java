package com.example.multithreading.immutable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 吕茂陈
 * @date 2021/12/20 19:21
 */
public class Main2 {

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        List<Integer> list = new CopyOnWriteArrayList<>();
        new WriterThread(list).start();
        new ReaderThread(list).start();
    }
}

class WriterThread extends Thread {
    private final List<Integer> list;

    public WriterThread(List<Integer> list) {
        super("WriterThread");
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            list.add(i);
            list.remove(0);
        }
    }
}

class ReaderThread extends Thread {

    private final List<Integer> list;

    public ReaderThread(List<Integer> list) {
        super("WriterThread");
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
//            方案二需要加锁
//            synchronized (list) {
                for (Integer n : list) {
                    System.out.println(n);
                }
//            }

        }
    }
}
