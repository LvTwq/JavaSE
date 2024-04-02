package com.example.designpatterns.machine;

import com.example.designpatterns.machine.service.StateAction;
import com.example.designpatterns.machine.service.StateCondition;
import com.example.designpatterns.machine.service.impl.*;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/22 17:43
 */
public enum CommodityState {
    // 待审核
    TO_AUDIT {
        @Override
        StateCondition getCondition() {
            return new ToAuditStateCondition();
        }

        @Override
        StateAction getAction() {
            return new ToAuditStateAction();
        }
    },
    // 已上架
    ON_SHELF {
        @Override
        StateCondition getCondition() {
            return new OnShelfStateCondition();
        }

        @Override
        StateAction getAction() {
            return new OnShelfStateAction();
        }
    },
    // 已下架
    OFF_SHELF {
        @Override
        StateCondition getCondition() {
            return new OffShelfStateCondition();
        }

        @Override
        StateAction getAction() {
            return new OffShelfStateAction();
        }
    };

    boolean transition(CommodityState target) {
        StateCondition condition = getCondition();
        if (condition.check(target)) {
            StateAction action = getAction();
            action.doAction();
            return true;
        }
        throw new IllegalArgumentException("当前状态不符合流转条件");
    }

    abstract StateCondition getCondition();

    abstract StateAction getAction();
}
