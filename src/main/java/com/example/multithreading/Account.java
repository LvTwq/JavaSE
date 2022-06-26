package com.example.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 账户类
 *
 * @author 吕茂陈
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Account {

    /**
     * 定义锁对象
     */
    private final Lock lock = new ReentrantLock();

    /**
     * 获得指定 Lock 对象对应的 Condition
     */
    private final Condition cond = lock.newCondition();

    /**
     * 定义一个ThreadLocal类型的变量，该变量是一个线程局部变量，每个线程都会保留该变量的一个副本
     */
    private ThreadLocal<String> name = new ThreadLocal<>();

    public String getName() {
        return name.get();
    }

    public void setName(String str) {
        this.name.set(str);
    }

    /**
     * 初始化name成员变量的构造器
     *
     * @param str
     */
    public Account(String str) {
        // 设置此线程局部变量中当前线程副本中的值
        this.name.set(str);
        // 返回此线程局部变量中当前线程副本中的值
        System.out.println("=====" + this.name.get());
    }

    /**
     * 账户编号
     */
    @Setter
    private String accountNo;

    /**
     * 账户余额，不允许随便修改，所以只提供get()方法
     */
    private double balance;

    /**
     * 标识账户中是否已有存款
     */
    private boolean flag = false;

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }


    /**
     * 用 synchronized 修饰的方法实例方法（非static方法）是同步方法；
     * 对于同步方法而言，无需显示指定同步监视器，同步方法的同步监视器是this，也就是调用该方法的对象，
     * 因此对于同一个Account账户而言，任意时刻只能有一个线程获得对Account对象的锁定
     * Account类是一个可变类，当两个线程同时修改Account对象的balance成员变量的值时，程序异常，
     * 所以要把对balance的访问设置成线程安全的，就只要把修改balance的方法变成同步方法即可
     *
     * @param drawAmount
     */
    public synchronized void draw(double drawAmount) {
        if (balance >= drawAmount) {
            System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmount);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 修改余额
            balance -= drawAmount;
            System.out.println("\t余额为：" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
        }
    }

    /**
     * draw2()显示使用Lock对象作为同步锁，而使用同步方法时系统隐式使用当前对象作为同步监视器
     * 同样符合“加锁——修改——释放锁”的操作模式，而且使用Lock对象时，每个Lock对象对应一个Account对象，
     * 一样可以保证对于同一个Account对象，同一时刻只有一个线程进入修改共享资源的代码区
     *
     * @param drawAmount
     */
    public void draw2(double drawAmount) {
        // 加锁
        lock.lock();
        try {
            if (balance >= drawAmount) {
                System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmount);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 修改余额
                balance -= drawAmount;
                System.out.println("\t余额为：" + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
            }
        } finally {
            lock.unlock();
        }
    }

    public synchronized void draw3(double drawAmount, int i) {
        if (!flag) {
            try {
                // 当前线程等待，直到其他线程调用该同步监视器的notify()或者notifyAll()方法来唤醒该线程；
                // 调用wait()方法的当前线程会释放对该同步监视器的锁定
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "，第" + i + "次取钱：" + drawAmount);
            balance -= drawAmount;
            System.out.println("账户余额为：" + balance);
            flag = false;
            // 唤醒在此同步监视器上的所有线程，只有当前线程放弃对该同步监视器的锁定后，才可以执行被唤醒的线程
            notifyAll();
        }
    }

    public void draw4(double drawAmount, int i) {
        // 显示地使用Lock对象充当同监视器，则需要使用Condition对象来暂停、唤醒指定线程
        lock.lock();
        try {
            if (!flag) {
                // 类似于隐式同步监视器的wait()方法，导致当前线程等待，直到其他线程调用该Condition的signal()或者signalAll()唤醒该线程
                cond.await();
            } else {
                System.out.println(Thread.currentThread().getName() + "，第" + i + "次取钱：" + drawAmount);
                balance -= drawAmount;
                System.out.println("账户余额为：" + balance);
                flag = false;
                /* signal()：唤醒在此Lock对象上等待的单个线程，如果所有线程都在该Lock对象上等待，则任意选择唤醒其中一个线程
                唤醒在此Lock对象上等待的所有线程，只有当前线程放弃对该Lock对象的锁定后，方才可以执行被唤醒的线程
                 */
                cond.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void deposit(double depositAmount, int i) {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "，第" + i + "次存款：" + depositAmount);
            balance += depositAmount;
            System.out.println("账户余额为：" + balance);
            flag = true;
            notifyAll();
        }
    }

    public void deposit2(double depositAmount, int i) {
        lock.lock();
        try {
            if (flag) {
                cond.await();
            } else {
                System.out.println(Thread.currentThread().getName() + "，第" + i + "次存款：" + depositAmount);
                balance += depositAmount;
                System.out.println("账户余额为：" + balance);
                flag = true;
                cond.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
