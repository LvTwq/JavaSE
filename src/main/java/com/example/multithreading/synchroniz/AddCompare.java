package com.example.multithreading.synchroniz;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用锁之前一定要理清楚，要保护的是什么逻辑，多线程的执行情况又是什么样的：
 * 线程、业务逻辑、锁 三者之间的关系
 *
 * @author 吕茂陈
 */
@Slf4j
public class AddCompare {

    volatile int a = 1;
    volatile int b = 1;

    /**
     * 只对add()方法加锁没用，因为这里的add()方法始终只有一个线程操作
     */
    public synchronized void add() {
        log.info("========开始相加=======");
        for (int i = 0; i < 10000; i++) {
            a++;
            b++;
        }
        log.info("========加完了=======");
    }

    public synchronized void compare() {
        log.info("========开始比较=======");
        for (int i = 0; i < 10000; i++) {
            if (a < b) {
                // 明明a<b才会进这个循环，但是也能输出 a>b 为true
                log.info("a：{}，b：{}，a>b：{}", a, b, a > b);
            }
        }
        log.info("=========比较完成========");
    }

    public static void main(String[] args) {
        /*
        之所以会错乱，是因为两个线程交错执行add和compare中的业务逻辑，而这些业务逻辑不是原子性的：
        a++ 和 b++ 操作中可以穿插在compare方法的比较代码中；
        更要注意的是，a<b 这种比较操作在字节码层面是加载a、加载b和比较，共三步，虽然是一行，但也不是原子性的
        所以正确的做法是：为add和compare都加上方法锁，确保add方法执行时，compare无法读取a和b
         */
        AddCompare addCompare = new AddCompare();
        new Thread(() -> addCompare.add()).start();
        new Thread(() -> addCompare.compare()).start();
    }
}
