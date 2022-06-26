package com.example.oop;

/**
 * @author Administrator
 */
public class StaticAccessNonStatic {
    public void info(){
        System.out.println("true = " + true);
    }

    public static void main(String[] args) {
        new StaticAccessNonStatic().info();
    }
}
