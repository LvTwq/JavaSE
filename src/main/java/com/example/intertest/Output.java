package com.example.intertest;

/**
 * @author 吕茂陈
 */
public interface Output {

    /**
     * 接口里定义的成员变量只能是常量
     */
    int MAX_CACHE_LINE = 50;

    /**
     * 普通方法不能有方法体，默认添加 public abstract 修饰
     */
    void out();

    void getData(String msg);

    /**
     * 默认方法必须要用 default 修饰，默认添加 public
     *
     * @param msgs
     */
    default void print(String... msgs) {
        for (String msg : msgs) {
            System.out.println(msg);
        }
    }

    default void test() {
        System.out.println("默认的test()方法");
    }

    /**
     * 类方法，必须用 static 修饰，默认添加 public
     *
     * @return
     */
    static String staticTest() {
        return "接口里的类方法";
    }


}
