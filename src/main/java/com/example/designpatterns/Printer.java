package com.example.designpatterns;

/**
 * @author 吕茂陈
 */
public class Printer implements Output, Product {

    private String[] printData = new String[MAX_CACHE_LINE];
    /**
     * 用以记录当前需打印的作业数
     */
    private int dataNum = 0;

    @Override
    public void out() {
        // 只要还有作业，就继续打印
        while (dataNum > 0) {
            System.out.println("打印机打印：" + printData[0]);
            // 把作业对列整体前移一位，并将剩下的作业数减1
            System.arraycopy(printData, 1, printData, 0, --dataNum);
        }
    }

    @Override
    public void getData(String msg) {
        if (dataNum >= MAX_CACHE_LINE) {
            System.out.println("输出对列已满，添加失败");
        } else {
            // 把打印数据添加到对列里，已保存的数据量加1
            printData[dataNum++] = msg;
        }
    }

    @Override
    public int getProduceTime() {
        return 45;
    }


    public static void main(String[] args) {
        Output o = new Printer();
        o.getData("语文");
        o.getData("数学");
        o.out();
        o.getData("英语");
        o.getData("化学");
        o.out();

        // 调用默认方法
        o.print("孙悟空", "猪八戒", "唐僧");
        o.test();

        Product p = new Printer();
        System.out.println(p.getProduceTime());

        // 所有接口类型的引用变量都可以直接赋给Object类型的变量
        Object obj = p;
        System.out.println(p);
    }
}
