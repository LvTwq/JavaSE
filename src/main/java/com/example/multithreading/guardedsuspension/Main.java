package com.example.multithreading.guardedsuspension;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 通过让线程等待保证实例的安全性
 *
 * @author 吕茂陈
 * @date 2021/12/28 19:37
 */
public class Main {

    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "Alice", 3141592L).start();
        new ServerThread(requestQueue, "Bobby", 6535897L).start();
    }
}

/**
 * 请求
 */
class Request {

    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}

/**
 * 用于存放请求的类
 * GuardedObject（被守护的对象）
 */
class RequestQueue {

    private final Queue<Request> queue = new LinkedList<>();

    public synchronized Request getRequest() {
        while (queue.peek() == null) {
            try {
                // 线程要执行某个实例的wait方法，必须获取该实例的锁（这里获取的是this的锁，所以this.省略）
                System.out.println(Thread.currentThread().getName() + "开始 wait()，queue为：" + queue);
                wait();
                System.out.println(Thread.currentThread().getName() + "wait() 结束，queue为：" + queue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 移除队列的第一个元素
        return queue.remove();
    }

    public synchronized void putRequest(Request request) {
        queue.offer(request);
        System.out.println(Thread.currentThread().getName() + "开始 notifyAll()，queue为：" + queue);
//      等同于 this.notifyAll(); 不等同于queue.notifyAll();
        /*
        由于执行notifyAll()的线程持有this的锁，所以执行notifyAll()之后，从等待队列中退出的其他线程会在获取锁时阻塞
        因此，其他线程的操作实际上并没有什么进展（也不会检查守护条件）
         */
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "notifyAll() 结束，queue为：" + queue);
    }
}

class RequestQueue2 {

    /**
     * LinkedBlockingQueue 已经实现了 BlockingQueue接口
     */
    private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    public Request getRequest() {
        Request req = null;
        try {
            // 队列为空，就会 wait
            req = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return req;
    }

    public void putRequest(Request request) {
        try {
            // take、put 方法已经做了互斥处理
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 发送请求的线程
 */
class ClientThread extends Thread {
    private final Random random;
    private final RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = new Request("No." + i);
            System.out.println(Thread.currentThread().getName() + " 发送 " + request);
            requestQueue.putRequest(request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 接收请求的线程
 */
class ServerThread extends Thread {
    private final Random random;
    private final RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName() + " 处理 " + request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
