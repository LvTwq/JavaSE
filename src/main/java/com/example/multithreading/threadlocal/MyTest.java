package com.example.multithreading.threadlocal;

import com.example.multithreading.Account;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class MyTest extends Thread {
    private final Account account;

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
            log.info("{}，账户的i值：{}", account.getName(), i);
        }
    }
}
