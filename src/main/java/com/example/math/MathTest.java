package com.example.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

/**
 * @author 吕茂陈
 * @date 2021/12/08 15:46
 */
public class MathTest {


    public static void main(String[] args) {
        // 精度丢失 0
        int i1 = 1;
        int i2 = 2;
//        System.out.println(i1 / i2);

        // 精度不丢失 0.5
        double f1 = 1d;
        double f2 = 3d;
        System.out.println((f1 / f2)*100);
        System.out.println(Math.round(f1/f2));

        // 精度不丢失 0.5
        double d1 = 1d;
        double d2 = 2d;
//        System.out.println(d1 / d2);

    }

    @Test
    public void test0() {
        float f = 0.5f;
        System.out.println(f);
        // 输出 0.12345679
        float f1 = 0.123456789123456789f;
        System.out.println(f1);

        double d = 0.5d;
        System.out.println(d);
        // 输出 0.12345678912345678
        double d1 = 0.123456789123456789d;
        System.out.println(d1);
        // 输出 0.12345678912345678
        BigDecimal doubleDec = BigDecimal.valueOf(d1);
        System.out.println(doubleDec);

        System.out.println("0.05 + 0.01 = " + (0.05 + 0.01));
        System.out.println("1.0 - 0.42 = " + (1.0 - 0.42));
        System.out.println("4.015 * 100 = " + (4.015 * 100));
        System.out.println("123.3 / 100 = " + (123.3 / 100));
    }


    @Test
    public void test01() {
        // 精度全部丢失
        int a = 1;
        int b = 2;
        System.out.println(a / 2);
        System.out.println((double) a / 2);
        System.out.println("=================");

        int c = a / b;
        System.out.println(c);
        System.out.println("==============");


        int i = a / b;
        System.out.println(i);
        System.out.println((double) a / (double) b);
    }

    @Test
    public void test02() {
        DecimalFormat decimalFormat = new DecimalFormat("##.00%");
        System.out.println(decimalFormat.format(1 / 3.0));
        double i = 100;
        double j = 300;
        System.out.println(decimalFormat.format(i / j));

        System.out.println("=========================================");
        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(2);
        System.out.println("百分数：" + nt.format(1.0 / 3));


    }

    @Test
    public void test04() {
        Person person = Person.builder().d(0).build();
        System.out.println(person);
    }

    @Test
    public void test05() {
        float twoThirds = 2 / 3; // Noncompliant; int division. Yields 0.0
        long millisInYear = 1_000 * 3_600 * 24 * 365; // Noncompliant; int multiplication. Yields 1471228928
        long bigNum = Integer.MAX_VALUE + 2; // Noncompliant. Yields -2147483647
        long bigNegNum = Integer.MIN_VALUE - 1; //Noncompliant, gives a positive result instead of a negative one.

    }

    public long compute(int factor) {
        return factor * 10_000;  //Noncompliant, won't produce the expected result if factor > 214_748
    }

    public float compute2(long factor) {
        return factor / 123;  //Noncompliant, will be rounded to closest long integer
    }


    @Test
    public void test06() {
        float twoThirds = 2f / 3; // 2 promoted to float. Yields 0.6666667
        long millisInYear = 1_000L * 3_600 * 24 * 365; // 1000 promoted to long. Yields 31_536_000_000
        long bigNum = Integer.MAX_VALUE + 2L; // 2 promoted to long. Yields 2_147_483_649
        long bigNegNum = Integer.MIN_VALUE - 1L; // Yields -2_147_483_649
    }
}
