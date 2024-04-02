package com.example.time;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 吕茂陈
 */
@Slf4j
public class LacalDateTest {

    public static void main(String[] args) {
        LocalDate d1 = LocalDate.now();
        log.info("d1:{}", d1);
        //获得2014年的第146天
        LocalDate d2 = LocalDate.ofYearDay(2014, 146);
        log.info("{}", d2);
        // 设置为2014年5月21日
        LocalDate d3 = LocalDate.of(2014, Month.MAY, 21);
        log.info("{}", d3);

        LocalTime now = LocalTime.now();
        log.info("{}", now);
        // 设置为22点23分
        now = LocalTime.of(22, 23);
        log.info("{}", now);
        //返回一天中的第5555秒
        LocalTime localTime = LocalTime.ofSecondOfDay(5555);
        log.info("localTime = {}", localTime);

        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime future = now1.plusHours(25).plusMinutes(3);
        log.info("{}", future);
    }


    @Test
    public void test01() {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_LOCAL_TIME,
                DateTimeFormatter.ISO_DATE_TIME,
                DateTimeFormatter.ISO_DATE,
                DateTimeFormatter.BASIC_ISO_DATE
        };
        LocalDateTime date = LocalDateTime.now();
        for (DateTimeFormatter formatter : formatters) {
            // 方法1
            log.info(date.format(formatter));
            // 方法2
//            log.info(formatter.format(date));
        }
    }

    @Test
    public void test02() {
        String today = LocalDate.now().minusDays(1L).format(DateTimeFormatter.ISO_LOCAL_DATE);
        log.info(today);

        String s1 = null;
        log.info(s1);

    }


    @Test
    public void test03() throws ParseException {
        String dateString = "周一 10月 18 16:49:28 2021";
        SimpleDateFormat inputFormat = new SimpleDateFormat("E MM月 dd HH:mm:ss yyyy");
        Date date = inputFormat.parse(dateString);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String outputString = outputFormat.format(date);
        System.out.println(outputString);

/*        "2023年6月6日"
        "6/6/2023"
        2023/6/6
        */
        String parse = DateUtil.parse("2023-06-06").toDateStr();
        System.out.println(parse.toString());
    }


    @Test
    public void test05() throws ParseException {
        String dateString = "6/6/2023";
        SimpleDateFormat inputFormat = new SimpleDateFormat("M/d/yyyy");


        Date date = inputFormat.parse(dateString);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String outputString = outputFormat.format(date);
        System.out.println(outputString);
    }

    @Test
    public void test06() throws ParseException {
        String dateString = "2023年6月6日";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy年M月d日");
        Date date = inputFormat.parse(dateString);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String outputString = outputFormat.format(date);
        System.out.println(outputString);
    }


    @Test
    public void test04() {
        Long t = 1678864312191L;
        String timestamp = DateTime.of(t).toString();
        System.out.println(DateUtil.current());
        System.out.println(timestamp);
    }
}
