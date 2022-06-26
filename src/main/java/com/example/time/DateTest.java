package com.example.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

/**
 * @author 吕茂陈
 */
public class DateTest {
    public static void main(String[] args) {
        Date d1 = new Date();
        // 获取当前时间100ms后的时间
        Date d2 = new Date(System.currentTimeMillis() + 100);
        System.out.println("d2 = " + d2);
        System.out.println(d1.compareTo(d2));
        System.out.println(d1.before(d2));
        System.out.println("d2 = " + d2.getTime());
    }

    @Test
    public void test01(){
        YearMonth date = YearMonth.now();
        // 2021-12
        System.out.println(date);
        // 2021
        System.out.println(date.getYear());
        // 12
        System.out.println(date.getMonthValue());
        // 当月最后一天：2021-12-31
        LocalDate localDate = date.atEndOfMonth();
        System.out.println(localDate);

        // 当月最后一天最后一秒：2021-12-31T23:59:59
        LocalDateTime localDateTime = localDate.atTime(23,59,59);
        System.out.println(localDateTime);

        // 2021-12-01T00:00
        LocalDateTime start = LocalDateTime.of(date.getYear(), date.getMonth(), 1, 0, 0, 0);
        System.out.println(start);
        // 2020-12-01T00:00
        LocalDateTime last = start.minusYears(1L);
        System.out.println(last);
        // 2020-12-31T23:59:59
        LocalDateTime localDateTime1 = localDateTime.minusYears(1L);
        System.out.println(localDateTime1);
    }

    @Test
    public void test02(){
        String decrypt = "20220124135527";
        String format = String.format("%s-%s-%sT%s:%s:%s", decrypt.substring(0, 4), decrypt.substring(4, 6),
                decrypt.substring(6, 8), decrypt.substring(8, 10), decrypt.substring(10, 12), decrypt.substring(12, 14));
        System.out.println(format);
        long ts = dateTime2TimeStamp(LocalDateTime.parse(format));
        long l = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - ts;
        System.out.println(l);
    }


    public static long dateTime2TimeStamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
