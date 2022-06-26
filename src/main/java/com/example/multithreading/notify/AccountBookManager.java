package com.example.multithreading.notify;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕茂陈
 * @date 2022/03/09 09:04
 */
public class AccountBookManager {

    List<Object> accounts = new ArrayList<>();

    synchronized boolean getAllRequiredAccountBook(Object from, Object to) {
        /*
         这里不能用if，唤醒线程到再次获取锁是有 时间差 的，当再次获取到🔒时，线程曾经要求的条件不一定满足
         所以需要重新进行条件判断，使用while：
         因为被唤醒的线程再次获取到🔒之后是从原来的wait之后开始执行的，wait在循环里，
         所以会再次进入循环条件，重新进行条件判断
         */
        while (accounts.contains(from) || accounts.contains(to)) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        } /*else{*/
            accounts.add(from);
            accounts.add(to);

            return true;
//        }
//        return false;
    }

    synchronized void releaseObtainedAccountBook(Object from, Object to) {
        accounts.remove(from);
        accounts.remove(to);
        /*
        尽量使用 notifyAll()

         */
        notifyAll();
    }
}
