package com.example.multithreading.singlethreadedexecution;

/**
 * @author 吕茂陈
 * @date 2021/12/13 20:22
 */
public class Main5 {
    public static void main(String[] args) {
        Tool spoon = new Tool("Spoon");
        Tool fork = new Tool("Fork");
        new EaterThread("Alice", spoon, fork).start();
        new EaterThread("Bobby", spoon, fork).start();
    }
}

class Tool {
    private final String name;

    public Tool(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + name + '}';
    }
}

class EaterThread extends Thread {
    private String name;
    private final Tool lefthand;
    private final Tool rightthand;

    public EaterThread(String name, Tool lefthand, Tool rightthand) {
        this.name = name;
        this.lefthand = lefthand;
        this.rightthand = rightthand;
    }

    @Override
    public void run() {
        while (true) {
            eat();
        }
    }

    public void eat() {
        synchronized (lefthand) {
            System.out.println(name + "，拿起" + lefthand + "（左手）");
            synchronized (rightthand) {
                System.out.println(name + "，拿起" + rightthand + "（右手）");
                System.out.println(name + "，正在吃！");
                System.out.println(name + "，放下" + rightthand + "（右手）");
            }
            System.out.println(name + "，放下" + lefthand + "（左手）");
        }
    }
}
