package com.example.collect;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * @author 吕茂陈
 * @date 2021/09/26 18:36
 */
public class ExampleHelperTest {

    @Test
    public void test01() {
        List<Integer> constValueList = ExampleHelper.CONST_VALUE_LIST;
        System.out.println(constValueList);
        Set<Integer> constValueSet = ExampleHelper.CONST_VALUE_SET;
        Map<Integer, String> constValueMap = ExampleHelper.CONST_VALUE_MAP;
    }
}
