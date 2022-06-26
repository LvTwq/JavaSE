package com.example.multithreading.immutable;

/**
 * @author 吕茂陈
 * @date 2021/12/21 08:31
 */
public class Main3 {

    public static void main(String[] args) {
        Point point = new Point(1, 2);
//        point.x = 2;
    }
}

class Line {

    private final Point startPoint;
    private final Point endPoint;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Line(int startx, int starty, int endx, int endy) {
        this.startPoint = new Point(startx, starty);
        this.endPoint = new Point(endx, endy);
    }

    public int getStartX() {
        return startPoint.getX();
    }

    public int getStartY() {
        return startPoint.getY();
    }

    public int getEndX() {
        return endPoint.getX();
    }

    public int getEndY() {
        return endPoint.getY();
    }
}

class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
