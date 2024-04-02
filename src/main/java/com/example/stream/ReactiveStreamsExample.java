package com.example.stream;

import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;


/**
 * @author 吕茂陈
 * @description
 * @date 2023/3/30 14:41
 */
public class ReactiveStreamsExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个 SubmissionPublisher 对象
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 创建一个 Subscriber 对象
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            private Subscription subscription;
            private int count;

            // 当订阅时被调用
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(1); // 请求一个数据
            }

            // 当接收到数据时被调用
            public void onNext(Integer item) {
                System.out.println("Received item: " + item);
                count++;
                if (count >= 5) {
                    subscription.cancel(); // 取消订阅
                    return;
                }
                this.subscription.request(1); // 请求一个数据
            }

            // 当出现错误时被调用
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            // 当完成时被调用
            public void onComplete() {
                System.out.println("Done");
            }
        };

        // 订阅
        publisher.subscribe(subscriber);

        // 发布数据
        for (int i = 0; i < 10; i++) {
            publisher.submit(i);
        }

        // 等待一段时间
        Thread.sleep(1000);

        // 关闭 publisher 对象
        publisher.close();
    }
}

