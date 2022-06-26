package com.example.multithreading;

/**
 * @author 吕茂陈
 */
public class DepositThread extends Thread{

    /**
     * 用户账户
     */
    private final Account account;

    /**
     * 当前取钱线程所希望取的钱数
     */
    private final double depositAmount;

    public DepositThread(String name, Account account, double depositAmount) {
        super(name);
        this.account = account;
        this.depositAmount = depositAmount;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.deposit(depositAmount, ++i);
        }
    }
}
