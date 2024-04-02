package com.example.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 吕茂陈
 * @description LRU 是最近最少使用的意思，当缓存容量达到上限，它会优先移除那些最久未被使用的数据，LRU是目前最常用的缓存算法
 * @date 2023/3/14 20:51
 */
public class Lru extends LinkedHashMap {

    int capacity;

    public Lru(int capacity) {
        /* accessOrder 参数是实现 LRU 的关键。
        当 accessOrder 的值为 true 时，将按照对象的访问顺序排序；
        当 accessOrder 的值为 false 时，将按照对象的插入顺序排序。
        我们上面提到过，按照访问顺序排序，其实就是 LRU
         */
        super(16, 0.75f, true);
        this.capacity = capacity;
    }


    /**
     * 向 LinkedHashMap 中添加新对象的时候，就会调用
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        // 当超出容量的时候返回 true，触发移除动作就可以了
        return size() > capacity;
    }
}
