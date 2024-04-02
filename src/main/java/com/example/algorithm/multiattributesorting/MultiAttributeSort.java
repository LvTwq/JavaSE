package com.example.algorithm.multiattributesorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 吕茂陈
 * @description 多属性排序算法
 * @date 2023/7/21 17:56
 */
public class MultiAttributeSort {

    public static void main(String[] args) {
        List<DataDemo> dataList = new ArrayList<>();
        // 添加数据到 dataList

        // 定义属性权重
        double createTimeWeight = 0.5;
        double priceWeight = 0.3;
        double regionWeight = 0.2;

        // 使用加权求和算法对数据进行排序
        Collections.sort(dataList, new MultiAttributeComparator(createTimeWeight, priceWeight, regionWeight));

        // 输出排序后的数据
        for (DataDemo data : dataList) {
            System.out.println(data);
        }
    }
}
