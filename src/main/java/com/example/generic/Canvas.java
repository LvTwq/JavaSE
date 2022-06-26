package com.example.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕茂陈
 */
public class Canvas {
    public void drawAll(List<? extends Shape> shapes){
        for (Shape s :
                shapes) {
            s.draw(this);
        }
    }

    public static void main(String[] args) {
        List<Circle> circleList = new ArrayList<>();
        Canvas c = new Canvas();
        c.drawAll(circleList);
    }
}
