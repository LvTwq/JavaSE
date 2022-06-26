package com.example.lambda;

/**
 * @author 吕茂陈
 */
public class LambdaQs {
    public void eat(Eatable e) {
        System.out.println(e);
        e.taste();
    }

    public void drive(Flyable f) {
        System.out.println("我正在驾驶：" + f);
        f.fly("晴天");
    }

    public void test(Addable add) {
        System.out.println("5和3的和为：" + add.add(5, 3));
    }

    public static void main(String[] args) {
        LambdaQs qs = new LambdaQs();
        qs.eat(() -> System.out.println("青果"));

        qs.drive(weather -> {
            System.out.println("今天天气是：" + weather);
            System.out.println("直升机飞行平稳");
        });

        qs.test((a, b) -> a + b);
    }
}
