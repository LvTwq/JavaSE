package com.example.multithreading.volatilelearn;

/**
 * @author 吕茂陈
 * @date 2021/11/24 19:37
 */
public class ApiTest {

    public static void main(String[] args) {

        final VT vt = new VT();

        Thread thread01 = new Thread(vt);
        Thread thread02 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                vt.sign = true;
                System.out.println("vt.sign = true 通知 while (!sign) 结束！");
            }
        });

        thread01.start();
        thread02.start();

    }
}

class VT implements Runnable {

    /**
     *
     */
    public boolean sign = false;

    @Override
    public void run() {
        while (!sign) {

        }
        System.out.println("你坏！");
    }
}
