package com.example.multithreading;

import org.junit.Test;

/**
 * @author 吕茂陈
 */
public class DrawTest {

    @Test
    public void test01() {
        // 创建一个账户
        Account account = new Account("账户编号", 1000);
        // 模拟两个线程对同一个账户取钱
        new DrawThread("甲", account, 800).start();
        new DrawThread("乙", account, 800).start();
    }

    public static void main(String[] args) {
        // 3个存款者线程共有300次尝试存款操作，但1个取款者线程只有100次尝试取钱操作，所以程序阻塞
        // 阻塞并不是死锁，取钱者线程已经执行结束，而存款者只是等待其他线程来取钱，而不是等待其他线程释放同步监视器
        Account acct = new Account("账户编号", 0);
        new DrawThread("取钱者", acct, 800).start();
        new DepositThread("存款者甲", acct, 800).start();
        new DepositThread("存款者乙", acct, 800).start();
        new DepositThread("存款者丙", acct, 800).start();
    }
}
