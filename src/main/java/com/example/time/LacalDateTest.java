package com.example.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * @author 吕茂陈
 */
public class LacalDateTest {

    public static void main(String[] args) {
        LocalDate d1 = LocalDate.now();
        System.out.println("d1:" + d1);
        //获得2014年的第146天
        LocalDate d2 = LocalDate.ofYearDay(2014, 146);
        System.out.println(d2);
        // 设置为2014年5月21日
        LocalDate d3 = LocalDate.of(2014, Month.MAY, 21);
        System.out.println(d3);
        System.out.println("======================");

        LocalTime now = LocalTime.now();
        System.out.println(now);
        // 设置为22点23分
        now = LocalTime.of(22, 23);
        System.out.println(now);
        //返回一天中的第5555秒
        LocalTime localTime = LocalTime.ofSecondOfDay(5555);
        System.out.println("localTime = " + localTime);
        System.out.println("======================");

        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime future = now1.plusHours(25).plusMinutes(3);
        System.out.println(future);
    }


    @Test
    public void test01() {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_DATE_TIME
        };
        LocalDateTime date = LocalDateTime.now();
        for (DateTimeFormatter formatter : formatters) {
            // 方法1
            System.out.println(date.format(formatter));
            // 方法2
            System.out.println(formatter.format(date));
        }
    }

    @Test
    public void test02() {
        String today = LocalDate.now().minusDays(1L).format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(today);

        String s1 = null;
        System.out.println(s1);

    }
}
