package com.example.lambda;

/**
 * @author 吕茂陈
 */
public class CommandTest2 {
    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] array = {3, -4, 6, 4};
        pa.process(array, (int element) ->
            System.out.println(element * element)
        );
    }
}
