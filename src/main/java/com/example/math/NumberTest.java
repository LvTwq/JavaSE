package com.example.math;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author 吕茂陈
 */
@Slf4j
public class NumberTest {

    @Test
    public void test01() {

        System.out.println(0.1 + 0.2);
        System.out.println(1.0 - 0.8);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);

        double amount1 = 2.15;
        double amount2 = 1.10;
        double amount3 = 100;
        if (amount1 - amount2 == 1.05) {
            System.out.println("OK");
        }

        System.out.println("=============使用 BigDecimal 类型===============");

        /*
        scale 表示小数点右边的位数，而 precision 表示精度，也就是有效数字的长度
         */
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println(new BigDecimal("1.0").subtract(new BigDecimal("0.8")));

        /*
        new BigDecimal("100") => scale=0、precision=3
        new BigDecimal("4.015") => scale=3、precision=4
        对于乘法操作，返回值的 scale 是两个数的 scale 相加，输出 401.500
         */
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100")));
        System.out.println(new BigDecimal("123.3").divide(new BigDecimal("100")));

        System.out.println("=============Double 转 BigDecimal 类型===============");

        // Double.toString(100) 为 100.0，所以 scale=1、precision=4，输出 401.5000
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal(Double.toString(100))));
    }

    @Test
    public void test02() {
        // 相当于 3.350xxx
        double num1 = 3.35;
        // 相当于 3.349xxx
        float num2 = 3.35f;
        // .1表示精确到小数点后1位 => 四舍五入，结果为 3.4
        System.out.printf("%.1f%n", num1);
        // 3.3
        System.out.printf("%.1f%n", num2);
    }


    @Test
    public void test03() {
        // equals 比较的是 BigDecimal 的 value 和 scale，1.0 的 scale 是 1，1 的 scale 是 0，所以结果一定是 false
        System.out.println(new BigDecimal("1.0").equals(new BigDecimal("1")));

        // 如果我们希望只比较 BigDecimal 的 value，可以使用 compareTo 方法
        System.out.println(new BigDecimal("1.0").compareTo(new BigDecimal("1")) == 0);

        // BigDecimal 的 equals 和 hashCode 方法会同时考虑 value 和 scale，如果结合 HashSet 或 HashMap 使用的话就可能会出现麻烦
        Set<BigDecimal> hashSet1 = new HashSet<>();
        hashSet1.add(new BigDecimal("1.0"));
        System.out.println(hashSet1.contains(new BigDecimal("1")));

        // 第一个方法是，使用 TreeSet 替换 HashSet。TreeSet 不使用 hashCode 方法，也不使用 equals 比较元素，而是使用 compareTo 方法，所以不会有问题
        Set<BigDecimal> treeSet = new TreeSet<>();
        treeSet.add(new BigDecimal("1.0"));
        System.out.println(treeSet.contains(new BigDecimal("1")));

        // 第二个方法是，把 BigDecimal 存入 HashSet 或 HashMap 前，先使用 stripTrailingZeros 方法去掉尾部的零，比较的时候也去掉尾部的 0，确保 value 相同的 BigDecimal，scale 也是一致的
        Set<BigDecimal> hashSet2 = new HashSet<>();
        hashSet2.add(new BigDecimal("1.0").stripTrailingZeros());
        System.out.println(hashSet2.contains(new BigDecimal("1.000").stripTrailingZeros()));
    }


    @Test
    public void test04() {
        long l = Long.MAX_VALUE;
        // 输出结果是一个负数，因为 Long 的最大值 +1 变为了 Long 的最小值
        // 显然这是发生了溢出，而且是默默地溢出，并没有任何异常
        System.out.println(l + 1);
        System.out.println(l + 1 == Long.MIN_VALUE);


        // 方法一是，考虑使用 Math 类的 addExact、subtractExact 等 xxExact 方法进行数值运算，这些方法可以在数值溢出时主动抛出异常
        try {
            System.out.println(Math.addExact(l, 1));
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        // 方法二是，使用大数类 BigInteger。BigDecimal 是处理浮点数的专家，而 BigInteger 则是对大数进行科学计算的专家
        BigInteger val = new BigInteger(String.valueOf(l));
        System.out.println(val.add(BigInteger.ONE));
        // 使用 BigInteger 对 Long 最大值进行 +1 操作；如果希望把计算结果转换一个 Long 变量的话，可以使用 BigInteger 的 longValueExact 方法，在转换出现溢出时，同样会抛出 ArithmeticException
        try {
            val.add(BigInteger.ONE).longValueExact();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test05() {
        BigDecimal bigDecimal = new BigDecimal("3.1415");
        log.info("{}", bigDecimal);
        log.info("{}", bigDecimal.toBigInteger());
        log.info("{}", bigDecimal.toBigInteger().intValue());
        log.info("{}", bigDecimal.toBigInteger().intValueExact());

    }


    @Test
    public void test06() {
        // 不保留小数点，进位
        String s = new BigDecimal("0.66").setScale(0, RoundingMode.HALF_UP).toPlainString();
        System.out.println(s);

        int i = new BigDecimal("1.66").setScale(0, RoundingMode.HALF_UP).intValue();
        System.out.println(i);

        String s1 = new BigDecimal("1").divide(new BigDecimal("3"), 0, RoundingMode.HALF_UP).setScale(0, RoundingMode.HALF_UP).toPlainString();
        System.out.println(s1);

        String s2 = new BigDecimal("1").divide(new BigDecimal("3"), 0, RoundingMode.UP).toPlainString();
        System.out.println(s2);



        double size = new BigDecimal(1).divide(new BigDecimal(60), 4, RoundingMode.UP).doubleValue();
        System.out.println(size);

        double size2 = new BigDecimal(1).divide(new BigDecimal(60), 4, RoundingMode.DOWN).doubleValue();
        System.out.println(size2);

        double size1 = 1 / 60;
        System.out.println(size1);

        RateLimiter limiter = RateLimiter.create(0.0167);
        System.out.println(limiter.getRate());
    }


    @Test
    public void test07() {
        Integer i = 1;
        Integer i1 = null;
        // false
        System.out.println(i.equals(null));
        // NullPointerException
        System.out.println(i1.equals(i));
    }

}
