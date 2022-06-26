package com.example.abstractlearn;

/**
 * @author 吕茂陈
 * @date 2021/12/26 13:40
 */
public class TrueGreet extends AbstractGreet implements Greet {

    /**
     * 留给子类来实现
     */
    @Override
    public void doCheer() {
        System.out.println("加油加油");
    }


    /**
     * 虽然我们也实现了Greet接口,但是因为AbstractGreet 已经实现过了接口,所以这里就不用再实现接口的cheer方法,
     * 只需要实现抽象类的doCheer()方法就可以了,当然也可以实现
     */
    @Override
    public void cheer() {
        super.cheer();
        System.out.println("子类自己的实现");
    }
}
