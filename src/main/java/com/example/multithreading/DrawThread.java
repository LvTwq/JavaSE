package com.example.multithreading;


/**
 * @author 吕茂陈
 */
public class DrawThread extends Thread {

    /**
     * 用户账户
     */
    private final Account account;

    /**
     * 当前取钱线程所希望取的钱数
     */
    private final double drawAmount;

    public DrawThread(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    /**
     * 甲乙两个线程并发取钱，有可能甲取钱成功，输出”甲取钱成功！吐出钞票：800.0“
     * 还没修改余额，乙也取了一次钱，输出”乙取钱成功！吐出钞票：800.0“
     * 这个时候，甲才执行到修改余额，”余额为：200.0“
     * 然后乙也会修改余额，”余额为：-600.0“
     */
    @Override
    public void run() {
        /*
         * 线程开始执行同步代码块之前，必须先获得对同步监视器的锁定；
         * 任何时刻只能有一个线程获得对同步监视器的锁定，当同步代码块执行完成后，该线程会释放对该同步监视器的锁定；
         * 虽然Java运行使用任何对象作为同步监视器，但是推荐使用可能被并发访问的共享资源充当同步监视器；
         * 此处使用account作为同步监视器，任何线程进入如下同步代码块之前，必须先获得对account账户的锁定——其他线程无法获得锁，也就无法修改它
         * 这种做法符合，“加锁——修改——释放锁”的逻辑
         */
//        synchronized (account) {
//            if (account.getBalance() >= drawAmount) {
//                System.out.println(getName() + "取钱成功！吐出钞票：" + drawAmount);
//                // 修改余额
//                account.setBalance(account.getBalance() - drawAmount);
//                System.out.println("\t余额为：" + account.getBalance());
//            } else {
//                System.out.println(getName() + "取钱失败！余额不足！");
//            }
//        }
        // 同步代码块结束，该线程释放锁

        /*
        直接调用account对象的draw()方法来执行取钱操作；
        同步方法的同步监视器是this，代表调用draw()方法的对象
        也就是说，线程进入draw()方法之前，必须先对account对象加锁
         */
//        account.draw(drawAmount);

        // 重复100次取钱操作
        for (int i = 0; i < 100; i++) {
            account.draw3(drawAmount, ++i);
        }
    }
}
