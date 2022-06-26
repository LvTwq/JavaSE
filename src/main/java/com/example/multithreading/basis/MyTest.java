package com.example.multithreading.basis;

import com.example.multithreading.Account;

public class MyTest extends Thread {
    private Account account;

    public MyTest(Account account, String name) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i == 6) {
                // 将账户名替换为当前线程名
                account.setName(getName());
            }
            System.out.println(account.getName() + "，账户的i值：" + i);
        }
    }
}
