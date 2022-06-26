package com.example.finaltest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 吕茂陈
 */
@Getter
@AllArgsConstructor
public class CacheImmutable {
    private static int MAX_SIZE = 10;
    /**
     * 使用数组来缓存已有的实例
     */
    private static CacheImmutable[] cache = new CacheImmutable[MAX_SIZE];
    /**
     * 记录缓存实例在缓存中的位置，cache[pos-1]是最新的缓存实例
     */
    private static int pos = 0;
    private final String name;

    public static CacheImmutable valueOf(String name) {
        // 遍历已缓存的对象
        for (int i = 0; i < MAX_SIZE; i++) {
            // 如果已有相同实例，则直接返回该缓存的实例
            if (cache[i] != null && cache[i].getName().equals(name)) {
                return cache[i];
            }
        }
        // 如果缓存已满
        if (pos == MAX_SIZE) {
            // 把缓存的第一个对象覆盖，即把刚刚生成的对象放在缓存池的最开始位置
            cache[0] = new CacheImmutable(name);
            // 把pos设为1
            pos = 1;
        } else {
            // 把新创建的对象缓存起来，pos+1
            cache[pos++] = new CacheImmutable(name);
        }
        return cache[pos - 1];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == CacheImmutable.class) {
            return name.equals(((CacheImmutable) obj).getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
