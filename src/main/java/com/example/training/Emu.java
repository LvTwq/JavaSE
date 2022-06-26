package com.example.training;

/**
 * @author 吕茂陈
 */
public class Emu {
    public static String s = "-";

    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (Exception e) {
            try {
                try {
                    String a = "Test";
                } catch (NullPointerException ex) {
                    s += "ic ";
                }
                throw new Exception();
            } catch (Exception x) {
                s += "mc ";
            } finally {
                s += "mf ";
            }
        } finally {
            s += "of ";
        }
        System.out.println(s);
    }
}
