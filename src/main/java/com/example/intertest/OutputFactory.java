package com.example.intertest;

/**
 * @author 吕茂陈
 */
public class OutputFactory {

    /**
     * 该方法负责创建 Output 实例，具体创建哪一个实现类的对象，由该方法决定
     * 如果系统需要将 Printer 改为 BetterPrinter实现类，只需要让 BetterPrinter实现 Output 接口，并修改此返回对象即可
     * @return 实现类的对象
     */
    public Output getOutput() {
        return new Printer();
    }

    public static void main(String[] args) {
        OutputFactory of = new OutputFactory();
        // of.getOutput() 生成一个 Printer作为参数传入 Computer ，Computer的构造函数需要一个 Output 类型的参数，而Printer实现了Output
        Computer c = new Computer(of.getOutput());
        c.keyIn("Java");
        c.keyIn("Python");
        c.print();
    }
}
