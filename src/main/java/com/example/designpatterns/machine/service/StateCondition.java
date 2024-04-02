package com.example.designpatterns.machine.service;

import com.example.designpatterns.machine.CommodityState;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/22 17:42
 */
public interface StateCondition {
    // 检查是否能流转到目标状态
    boolean check(CommodityState target);
}
