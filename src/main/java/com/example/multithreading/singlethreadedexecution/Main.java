package com.example.multithreading.singlethreadedexecution;

/**
 * @author 吕茂陈
 * @date 2021/12/03 08:44
 */
public class Main {

    public static void main(String[] args) {
        Gate gate = new Gate();
        // 3个线程，操作同一个gate
        new UserThread(gate, "Alice", "Alaska").start();
        new UserThread(gate, "Bobby", "Brazil").start();
        new UserThread(gate, "Chris", "Canada").start();

    }
}

class Gate {
    private int counter;

    private String name;

    private String address;

    public void pass(String name, String address) {
        this.counter++;
        this.name = name;
        this.address = address;
        // 延长临界区，可以提高检查出错误的可能性，counter较小时就能发现问题
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        check();
    }

    @Override
    public String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    /**
     * 检查最后一个通行者的记录数据，当姓名、地址 的首字母不同时，输出
     * 但由于 gate 线程不安全，某个线程执行该方法时，其他线程不断执行pass方法，写入name、address
     */
    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println(this);
        }
    }
}

class UserThread extends Thread {
    private final Gate gate;
    private final String myname;
    private final String myaddress;

    public UserThread(Gate gate, String myname, String myaddress) {
        this.gate = gate;
        this.myname = myname;
        this.myaddress = myaddress;
    }

    @Override
    public void run() {
        System.out.println(myname + " 开始通过门");
        while (true) {
            gate.pass(myname, myaddress);
        }
    }
}
