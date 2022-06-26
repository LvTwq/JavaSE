package com.example.serializable;

import java.nio.CharBuffer;

/**
 * @author 吕茂陈
 */
public class BufferTest {

    public static void main(String[] args) {
        // 通过静态方法allocate()创建buffer，此时该Buffer的capacity和limit为8，position为0
        CharBuffer buff = CharBuffer.allocate(8);
        System.out.println("capacity:" + buff.capacity());
        System.out.println("limit:" + buff.limit());
        System.out.println("position:" + buff.position());

        // 放入元素
        buff.put('a');
        buff.put('b');
        buff.put('c');
        System.out.println("加入3个元素后，position:" + buff.position());

        // flip()
//        CharBuffer flip = buff.flip();
    }
}
