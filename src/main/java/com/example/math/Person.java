package com.example.math;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 吕茂陈
 * @date 2021/12/08 16:28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    int i;

    double d;

    float f;

    BigDecimal b;
}
