package com.example.stream.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 顾客类
 *
 * @author 吕茂陈
 * @date 2022-07-06 15:54
 */
@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;//顾客姓名
}
