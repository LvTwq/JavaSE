package com.example.time;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author 吕茂陈
 */
@Slf4j
public class DateTest {
    public static void main(String[] args) {
        Date d1 = new Date();
        // 获取当前时间100ms后的时间
        Date d2 = new Date(System.currentTimeMillis() + 100);
        log.info("d2 = {}", d2);
        log.info("{}", d1.compareTo(d2));
        log.info("{}", d1.before(d2));
        log.info("d2 = {}", d2.getTime());
    }

    @Test
    public void test01() {
        YearMonth date = YearMonth.now();
        // 2021-12
        log.info("{}", date);
        // 2021
        log.info("{}", date.getYear());
        // 12
        log.info("{}", date.getMonthValue());
        // 当月最后一天：2021-12-31
        LocalDate localDate = date.atEndOfMonth();
        log.info("{}", localDate);

        // 当月最后一天最后一秒：2021-12-31T23:59:59
        LocalDateTime localDateTime = localDate.atTime(23, 59, 59);
        log.info("{}", localDateTime);

        // 2021-12-01T00:00
        LocalDateTime start = LocalDateTime.of(date.getYear(), date.getMonth(), 1, 0, 0, 0);
        log.info("{}", start);
        // 2020-12-01T00:00
        LocalDateTime last = start.minusYears(1L);
        log.info("{}", last);
        // 2020-12-31T23:59:59
        LocalDateTime localDateTime1 = localDateTime.minusYears(1L);
        log.info("{}", localDateTime1);
    }

    @Test
    public void test02() {
        String decrypt = "20220124135527";
        String format = String.format("%s-%s-%sT%s:%s:%s", decrypt.substring(0, 4), decrypt.substring(4, 6),
                decrypt.substring(6, 8), decrypt.substring(8, 10), decrypt.substring(10, 12), decrypt.substring(12, 14));
        log.info(format);
        long ts = dateTime2TimeStamp(LocalDateTime.parse(format));
        long l = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - ts;
        log.info("{}", l);
    }


    public static long dateTime2TimeStamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    @Test
    public void test03() {
        Instant now = Instant.now();
        log.info("{}", now);
        long epochSecond = Instant.now().getEpochSecond();
        log.info("时间戳，秒：{}", epochSecond);
        long l = Instant.now().toEpochMilli();
        log.info("时间戳，毫秒{}", l);
    }


    @Test
    public void testPeriod() {
        LocalDate startDate = LocalDate.of(2015, 2, 20);
        LocalDate endDate = LocalDate.of(2017, 1, 15);

        // 日期差
        Period period = Period.between(startDate, endDate);
        log.info("Years:" + period.getYears() +
                " months:" + period.getMonths() +
                " days:" + period.getDays());
        log.info("endDate 是否早于 startDate ：{}", period.isNegative());

        Period fromUnits = Period.of(3, 10, 10);
        Period fromDays = Period.ofDays(50);
        Period fromMonths = Period.ofMonths(5);
        Period fromYears = Period.ofYears(10);
        Period fromWeeks = Period.ofWeeks(40);

        assertEquals(280, fromWeeks.getDays());

    }

    @Test
    public void testDuration() {
        //Duration类表示秒或纳秒时间间隔，适合处理较短的时间，需要更高的精确性。我们能使用between()方法比较两个瞬间的差
        Instant start = Instant.parse("2017-10-03T10:15:30.00Z");
        Instant end = Instant.parse("2017-10-03T10:16:30.00Z");

        Duration duration = Duration.between(start, end);
        log.info("{}", duration.getSeconds());
        log.info("endDate 是否早于 startDate ：{}", duration.isNegative());

        LocalTime start2 = LocalTime.of(1, 20, 25, 1024);
        LocalTime end2 = LocalTime.of(3, 22, 27, 1544);

        log.info("{}", Duration.between(start2, end2).getSeconds());

        log.info("{}", Duration.ofMinutes(1).getSeconds());

    }


    @Test
    public void test09() {
        Duration duration = Duration.between(LocalTime.parse("2023-02-14T09:48:28", DateTimeFormatter.ISO_LOCAL_DATE_TIME), LocalDateTime.now());
        long day = duration.get(ChronoUnit.DAYS);
        long hour = duration.get(ChronoUnit.HOURS) - day * 24;
        long day1 = ChronoUnit.DAYS.between(LocalTime.parse("2023-02-14T09:48:28", DateTimeFormatter.ISO_LOCAL_DATE_TIME), LocalDateTime.now());
        long hour2 = ChronoUnit.HOURS.between(LocalTime.parse("2023-02-14T09:48:28", DateTimeFormatter.ISO_LOCAL_DATE_TIME), LocalDateTime.now());
        log.info("{}", day);
        log.info("{}", hour);
    }

    @Test
    public void test10() {
        DateTime start = DateUtil.parse("2023-02-12 09:48:28");
        String s = DateUtil.formatBetween(start, new Date());
        System.out.println(s);
    }


    @Test
    public void test11() {
        int second = DateUtil.second(new Date());
        log.info("{}", second);
    }


    @Test
    public void test12() {
//        long start = LocalDateTime.now().minusHours(1).toEpochSecond(ZoneOffset.of("+8"));
        long start = DateUtil.currentSeconds();

        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(start), ZoneId.systemDefault());
        String localDateTime = dateTime
                .format(DateTimeFormatter.ISO_DATE_TIME);
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTime.format(ftf);
        log.info("{}\n{}\n{}", start, localDateTime, format);
    }


    @Test
    public void test13() {
        long start = DateUtil.currentSeconds();
        double start1 = (double)DateUtil.currentSeconds();
        Double start2 = Double.valueOf(String.valueOf(start));
        double start3 = new BigDecimal(String.valueOf(start)).doubleValue();
        double maxValue = Double.MAX_VALUE;
        log.info("{}", start);
        log.info("{}", start1);
        log.info("{}", start2);
        log.info("{}", maxValue);
        log.info("{}", start3);
        double s = 1681803419d;
        log.info("{}", s);

        DecimalFormat df = new DecimalFormat("#");
        String format = df.format(start);
        log.info("{}", format);
        Double aDouble = new Double("1681807.989");
        log.info("double value is {}", aDouble);
    }

    private final DecimalFormat df = new DecimalFormat("#");

    @Test
    public void test14() {
        double epochSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        String score = df.format(epochSecond);
        log.info("{}", score);

        long myLong = 1234567890123456L;
        System.out.println(Double.parseDouble(score));
        System.out.println(new BigDecimal(score).doubleValue());
        BigDecimal bigDecimal = new BigDecimal(score, MathContext.DECIMAL64);
        System.out.println(bigDecimal.toString());
        String scoreString = String.format("%.0f", new BigDecimal(score, MathContext.DECIMAL64));
        System.out.println(Double.parseDouble(scoreString));


        BigDecimal scoreDecimal = new BigDecimal(score, MathContext.DECIMAL64);
        System.out.println(scoreDecimal.doubleValue());
    }


    @Test
    public void test15() {
        long epochSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        double score = new BigDecimal(String.valueOf(epochSecond)).divide(new BigDecimal("1000"), 3, RoundingMode.UP).doubleValue();
        System.out.println(score);

        long longValue = new BigDecimal(String.valueOf(Double.parseDouble("1681868.983"))).multiply(new BigDecimal("1000")).longValue();
        System.out.println(longValue);
    }


    @Test
    public void test16(){
        DateTime dateTime = DateUtil.beginOfHour(new Date());
        System.out.println(dateTime.toString());
        DateTime dateTime1 = DateUtil.offsetHour(dateTime, 1);
        System.out.println(dateTime1);
        System.out.println(dateTime.toString());

        Date date = new Date();
        String format = DateUtil.format(date, DatePattern.NORM_DATETIME_PATTERN);
        log.info("预处理应用访问数据 时间 {}", format);
        String day = DateUtil.format(date, DatePattern.NORM_DATE_PATTERN);
        Date dayDate = DateUtil.parse(day, DatePattern.NORM_DATE_PATTERN).toJdkDate();
            log.info("day:{},dayDate:{}", day, DateTime.of(date));

        DateTime nextTask = DateUtil.beginOfHour(new Date());
        System.out.println(nextTask);

        DateTime dateTime2 = DateUtil.offsetHour(nextTask, 1);
        System.out.println(dateTime2);
    }


    @Test
    public void test17() {
        DateTime date = DateUtil.date(1697021569*1000L);
        date.toString();
    }


    @Test
    public void test18() {
        double v = Double.parseDouble("2.5875000000000004");
        System.out.println(v);
    }
}
