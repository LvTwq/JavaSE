package com.example.datastructure;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/3/27 17:29
 */
public class MyBitMap {

    private int[] bitArray;

    public MyBitMap(int size) {
        bitArray = new int[(size + 31) / 32];
    }

    public void set(int num) {
        int index = num / 32;
        int bitPos = num % 32;
        bitArray[index] |= (1 << bitPos);
    }

    public boolean get(int num) {
        int index = num / 32;
        int bitPos = num % 32;
        return (bitArray[index] & (1 << bitPos)) != 0;
    }
}
