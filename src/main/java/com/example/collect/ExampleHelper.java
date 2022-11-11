package com.example.collect;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * @author 吕茂陈
 * @date 2021/09/26 17:59
 */
public final class ExampleHelper {

    /**
     * 用于把可变集合对象变为不可变（不可修改，修改时会抛出UnsupportedOperationException异常）集合对象。所以，可以利用这套方法定义集合静态常量
     */
    public static final List<Integer> CONST_VALUE_LIST = Collections.unmodifiableList(Arrays.asList(1, 2, 3));

    public static final Set<Integer> CONST_VALUE_SET = Collections.unmodifiableSet(new HashSet<>(Sets.newHashSet(1, 2, 3)));

    public static final Map<Integer, String> CONST_VALUE_MAP;


    static {
        Map<Integer, String> valueMap = new HashMap<>();
        valueMap.put(1, "value1");
        valueMap.put(2, "value2");
        valueMap.put(3, "value3");
        CONST_VALUE_MAP = Collections.unmodifiableMap(valueMap);
    }


}
