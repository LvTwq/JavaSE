package com.example.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/6 17:31
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PolicyLogCollect {

    /**
     * 触发的策略名
     */
    private String targetPolicyName;
    /**
     * 经过该策略的描述
     */
    private String policyResultDes;

}