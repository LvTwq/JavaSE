package com.example.generic;

public class Stove {

    private Meat meat;

    static <T> T heat(T food) {
        System.out.println(food);
        return food;
    }

    public static void main(String[] args) {
        Meat meat = new Meat();
        meat = Stove.heat(meat);
        System.out.println(meat);

        Sou sou = new Sou();
        sou = heat(sou);
        System.out.println(sou);
    }
}
