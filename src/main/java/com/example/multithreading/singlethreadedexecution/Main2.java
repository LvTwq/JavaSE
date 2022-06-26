package com.example.multithreading.singlethreadedexecution;

/**
 * Single Threaded Execution 模式
 * 用于设置限制，确保同一时间内只能让一个线程执行处理
 *
 * @author 吕茂陈
 * @date 2021/12/03 08:44
 */
public class Main2 {

    public static void main(String[] args) {
        Gate2 gate = new Gate2();
        // 4个线程，操作同一个gate
        new UserThread2(gate, "Alice", "Alaska").start();
        new UserThread2(gate, "Bobby", "Brazil").start();
        new UserThread2(gate, "Chris", "Canada").start();
        new UserThread2(gate, "Dog", "Doilies").start();
    }
}

class Gate2 {

    /**
     * 声明为private，便于确认类的安全，
     */
    private int counter;

    private String name = "Nobody";

    private String address = "Nowhere";

    /**
     * 可能被多个线程交错执行，某个线程执行check()方法时，其他线程不断执行pass方法，写入name、address
     *
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        this.name = name;
        this.address = address;
        check();
    }

    /**
     *
     * @return
     */
    @Override
    public synchronized String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    /**
     * 检查最后一个通行者的记录数据，当姓名、地址 的首字母不同时，输出
     * 为什么check()不用加synchronized：
     * 1、因为只有pass方法调用，而pass方法已经声明为synchronized；
     * 2、且check方法声明为private，不可能从类外部调用该方法
     */
    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println(this);
        }
    }
}

class UserThread2 extends Thread {
    private final Gate2 gate;
    private final String myname;
    private final String myaddress;

    public UserThread2(Gate2 gate, String myname, String myaddress) {
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
