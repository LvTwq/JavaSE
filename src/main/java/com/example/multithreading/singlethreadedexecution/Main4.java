package com.example.multithreading.singlethreadedexecution;

/**
 * @author 吕茂陈
 * @date 2021/12/13 09:16
 */
public class Main4 {

    public static void main(String[] args) {

        for (int trial = 0; true; trial++) {
            SecurityGate gate = new SecurityGate();
            CrackerThread[] t = new CrackerThread[5];

            // 启动 CrackerThread
            for (int i = 0; i < t.length; i++) {
                t[i] = new CrackerThread(gate);
                t[i].start();
            }

            // 等待 CrackerThread 终止
            for (int i = 0; i < t.length; i++) {
                try {
                    t[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 由于enter和exit的调用次数是相同的，所以每次循环到最外层的for时，getCounter都应该为0
            if (gate.getCounter() == 0) {
                System.out.println("无矛盾");
            } else {
                System.out.println("CrackerThread 不安全！");
                System.out.println("getCounter() == " + gate.getCounter());
                System.out.println("trial = " + trial);
                break;
            }

        }
    }
}

class CrackerThread extends Thread {
    private final SecurityGate gate;

    public CrackerThread(SecurityGate gate) {
        this.gate = gate;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            gate.enter();
            gate.exit();
        }
    }
}

class SecurityGate {
    /**
     * 如果这个字段不声明为 volatile，不管其他线程怎么修改 counter，都只是对各自持有线程的缓存执行操作
     */
    private volatile int counter = 0;

    public synchronized void enter() {
        int currentCounter = counter;
        Thread.yield();
        counter = currentCounter + 1;
    }

    public synchronized void exit() {
        int currentCounter = counter;
        Thread.yield();
        counter = currentCounter - 1;
    }

    public int getCounter() {
        return counter;
    }
}
