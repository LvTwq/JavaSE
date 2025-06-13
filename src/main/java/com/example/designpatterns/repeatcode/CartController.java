package com.example.designpatterns.repeatcode;

public class CartController {


//    @GetMapping("wrong")
//    public Cart wrong(@RequestParam("userId") int userId) {
//        //根据用户ID获得用户类型
//        String userCategory = Db.getUserCategory(userId);
//        //普通用户处理逻辑
//        if (userCategory.equals("Normal")) {
//            NormalUserCart normalUserCart = new NormalUserCart();
//            return normalUserCart.process(userId, items);
//        }
//        //VIP用户处理逻辑
//        if (userCategory.equals("Vip")) {
//            VipUserCart vipUserCart = new VipUserCart();
//            return vipUserCart.process(userId, items);
//        }
//        //内部用户处理逻辑
//        if (userCategory.equals("Internal")) {
//            InternalUserCart internalUserCart = new InternalUserCart();
//            return internalUserCart.process(userId, items);
//        }
//
//        return null;
//    }
//
//
//    @GetMapping("right")
//    public Cart right(@RequestParam("userId") int userId) {
//        String userCategory = Db.getUserCategory(userId);
//        AbstractCart cart = (AbstractCart) applicationContext.getBean(userCategory + "UserCart");
//        return cart.process(userId, items);
//    }
}
