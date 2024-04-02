package com.example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/2 17:28
 */
public class HeapDumpMain {

    static class HeapDumpObject {
        String str ="1234567890";
    }

    public static void main(String[] args) {
        List<HeapDumpObject> ooms = new ArrayList<>();
        while (true) {
            ooms.add(new HeapDumpObject());
        }
    }

}
