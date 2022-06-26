package com.example.polymorphism;

import lombok.extern.slf4j.Slf4j;

/**
 * 接口可以多继承
 *
 * @author 吕茂陈
 * @date 2022/03/14 08:56
 */
public interface InterC extends InterA, InterB {

    @Override
    void run();
}

@Slf4j
class Main {
    public static void main(String[] args) {
        InterC interC = new InterC() {
            @Override
            public void run() {
                log.info("!!!");
            }
        };
        interC.run();
    }
}
