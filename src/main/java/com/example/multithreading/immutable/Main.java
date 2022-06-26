package com.example.multithreading.immutable;

/**
 * @author 吕茂陈
 * @date 2021/12/15 09:10
 */
public class Main {
    public static void main(String[] args) {
//        Person alice = new Person("Alice", "Alaska");
//        new PrintPersonThread(alice).start();
//        new PrintPersonThread(alice).start();
//        new PrintPersonThread(alice).start();

        Integer i = new Integer(1);
        i = new Integer(2);
        System.out.println(i);
    }
}

/**
 * Immutable 角色，实例被创建后，状态不再发生变化
 */
final class Person {
    private final String name;
    private final String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

class PrintPersonThread extends Thread {
    private Person person;

    public PrintPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "，打印 " + person);
        }
    }
}
