package com.example.repeatcode;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

import lombok.Builder;
import lombok.Data;

/**
 * 购物车中的商品
 */
@Data
@Builder
public class Item {
    public ReentrantLock lock;
    //商品ID
    private long id;
    // 商品数量
    private int quantity;
    // 商品单价
    private BigDecimal price;
    // 商品优惠
    private BigDecimal couponPrice;
    // 商品运费
    private BigDecimal deliveryPrice;
}
