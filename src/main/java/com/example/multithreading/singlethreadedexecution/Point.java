package com.example.multithreading.singlethreadedexecution;

/**
 * @author 吕茂陈
 * @date 2021/12/06 09:18
 */
public final class Point {

    private int x;

    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}

class PointEx {

    public static void main(String[] args) {
        Point point = new Point(1, 2);
        System.out.println(point);
    }

}
