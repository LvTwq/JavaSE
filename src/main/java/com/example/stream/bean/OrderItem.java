package com.example.stream.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单商品类
 *
 * @author 吕茂陈
 * @date 2022-07-06 15:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long productId;//商品ID
    private String productName;//商品名称
    private Double productPrice;//商品价格
    private Integer productQuantity;//商品数量
}
