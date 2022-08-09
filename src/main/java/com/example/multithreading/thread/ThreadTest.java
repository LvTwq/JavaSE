package com.example.multithreading.thread;

/**
 * @author 吕茂陈
 * @date 2022-08-04 09:46
 */
public class ThreadTest {

    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100000);
                } catch (Exception e) {
                }
            }).start();
        }
        Thread t = new Thread() {
            public void run() {
                int i = 0;
                while (true) {
                    i = (i++) / 100;
                }
            }
        };
        t.setName("Busiest Thread");
        t.start();
    }
}
