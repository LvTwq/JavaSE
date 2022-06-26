package com.example.multithreading;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2021/10/19 08:51
 */
@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class Blank {

    private String name;

    private int money;

    public int withdraw(int i) {
        if (money >= i) {
            log.info(Thread.currentThread().getName() + "，正在取钱！！！");
            return this.money - i;
        }
        log.warn("存款不够！！");
        return 0;
    }


}

