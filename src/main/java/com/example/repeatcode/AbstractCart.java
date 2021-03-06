package com.example.repeatcode;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处理购物车的大量重复逻辑在父类实现
 */
public abstract class AbstractCart {

    /**
     * @param userId
     * @param items  （Key 是商品 ID，Value 是商品数量）
     * @return 购物车类型 Cart
     */
    public Cart process(long userId, Map<Long, Integer> items) {

        Cart cart = new Cart();

        List<Item> itemList = new ArrayList<>();
        items.forEach((key, value) -> {
            Item item = Item.builder().id(key).quantity(value).build();
            itemList.add(item);
        });
        cart.setItems(itemList);
        //让子类处理每一个商品的优惠
        itemList.forEach(item -> {
            processCouponPrice(userId, item);
            processDeliveryPrice(userId, item);
        });
        //计算商品总价
        cart.setTotalItemPrice(cart.getItems().stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算总运费
        cart.setTotalDeliveryPrice(cart.getItems().stream().map(Item::getDeliveryPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算总折扣
        cart.setTotalDiscount(cart.getItems().stream().map(Item::getCouponPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算应付价格
        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
        return cart;
    }

    //处理商品优惠的逻辑留给子类实现
    protected abstract void processCouponPrice(long userId, Item item);

    //处理配送费的逻辑留给子类实现
    protected abstract void processDeliveryPrice(long userId, Item item);
}
