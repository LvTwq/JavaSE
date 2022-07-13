package com.example.stream.bean;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单类
 *
 * @author 吕茂陈
 * @date 2022-07-06 15:53
 */
@Data
public class Order {
    private Long id;
    private Long customerId;//顾客ID
    private String customerName;//顾客姓名
    private List<OrderItem> orderItemList;//订单商品明细
    private Double totalPrice;//总价格
    private LocalDateTime placedAt;//下单时间
}

