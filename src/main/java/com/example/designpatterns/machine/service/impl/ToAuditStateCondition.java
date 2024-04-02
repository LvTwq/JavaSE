package com.example.designpatterns.machine.service.impl;

import com.example.designpatterns.machine.CommodityState;
import com.example.designpatterns.machine.service.StateCondition;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/22 17:43
 */
public class ToAuditStateCondition implements StateCondition {
    @Override
    public boolean check(CommodityState target) {
        return false;
    }
}
