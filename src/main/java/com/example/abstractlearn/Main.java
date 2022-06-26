package com.example.abstractlearn;

/**
 * @author 吕茂陈
 * @date 2021/12/26 13:45
 */
public class Main {

    public static void main(String[] args) {
        Greet g1 = new TrueGreet();
        g1.cheer();
        System.out.println("=============================================");
        AbstractGreet g2 = new TrueGreet();
        g2.cheer();
        System.out.println("=============================================");
        TrueGreet g3 = new TrueGreet();
        g3.cheer();
    }
}
