package com.example.designpatterns.singleton;

/**
 * @author 吕茂陈
 * @description 使用 enum 实现懒加载的单例
 * @date 2023/3/28 10:52
 */
public class EnumSingleton {

    private EnumSingleton() {
    }


    public static EnumSingleton getInstance() {
        return Holder.HOLDER.instance;
    }

    private enum Holder {
        HOLDER;
        private final EnumSingleton instance;

        Holder() {
            instance = new EnumSingleton();
        }
    }
}
