package com.example.multithreading.guardedsuspension;

/**
 * @author 吕茂陈
 * @date 2022/01/17 08:51
 */
public class Main2 {

    public static void main(String[] args) {
        RequestQueue requestQueue1 = new RequestQueue();
        RequestQueue requestQueue2 = new RequestQueue();
        requestQueue1.putRequest(new Request("Hello"));
        new TalkThread(requestQueue1, requestQueue2, "Alice").start();
        new TalkThread(requestQueue2, requestQueue1, "Bobby").start();
    }
}

class TalkThread extends Thread {
    private final RequestQueue input;
    private final RequestQueue output;

    public TalkThread(RequestQueue input, RequestQueue output, String name) {
        super(name);
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "，开始！");

        for (int i = 0; i < 20; i++) {
            //接收对方的请求
            Request request1 = input.getRequest();
            System.out.println(Thread.currentThread().getName() + "，gets" + request1);

            // 加上一个感叹号再返回给对方
            Request request2 = new Request(request1.getName() + "!");
            System.out.println(Thread.currentThread().getName() + "，puts" + request2);
            output.putRequest(request2);
        }
        System.out.println(Thread.currentThread().getName() + "，结束！");
    }
}
