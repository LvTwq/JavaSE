package com.example.innertest;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 吕茂陈
 */
@Data
@AllArgsConstructor
public class Cow {

    private double weight;

    private class CowLeg {
        private double length;
        private String color;

        public CowLeg(double length, String color) {
            this.length = length;
            this.color = color;
        }

        public void info() {
            System.out.println("当前奶牛的颜色是：" + color + "，高：" + length);
            System.out.println("本牛腿所在的奶牛重：" + weight);
        }
    }

    public void test() {
        CowLeg leg = new CowLeg(1.12, "黑白相间");
        leg.info();

    }

    public static void main(String[] args) {
        Cow cow = new Cow(378.9);
        cow.test();
    }

}
