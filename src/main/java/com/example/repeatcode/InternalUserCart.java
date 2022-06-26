package com.example.repeatcode;


import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service(value = "InternalUserCart")
public class InternalUserCart extends AbstractCart {
    @Override
    protected void processCouponPrice(long userId, Item item) {
        item.setCouponPrice(BigDecimal.ZERO);
    }

    @Override
    protected void processDeliveryPrice(long userId, Item item) {
        item.setDeliveryPrice(BigDecimal.ZERO);
    }
}
