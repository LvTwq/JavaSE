package com.example.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import static java.text.DateFormat.MEDIUM;
import static java.util.TimeZone.SHORT;

/**
 * @author 吕茂陈
 */
@Slf4j
public class DateFormatTest {
    public static void main(String[] args) throws ParseException {
        // 需要被格式化的时间
        Date date = new Date();
        DateFormat[] df = new DateFormat[16];
        Locale[] locales = {Locale.CHINA, Locale.US};
        for (int i = 0; i < locales.length; i++) {
            df[i * 8] = DateFormat.getDateInstance(SHORT, locales[i]);
            df[i * 8 + 1] = DateFormat.getDateInstance(MEDIUM, locales[i]);
        }

        String str1 = "2017/10/07";
        String str2 = "2017年10月07日";
        System.out.println(DateFormat.getDateInstance().parse(str2));
//        System.out.println(DateFormat.getDateInstance(SHORT).parse(str1));
//        System.out.println(DateFormat.getDateInstance().parse(str1));
    }


    @Test
    public void test01() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 2022-02-08
        System.out.println(sdf.format(new Date()));

        String format = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(format);
    }

    @Test
    public void test02() {
        String jssj = "1986-04-08";
        String str = jssj + "T23:59:59";
        LocalDateTime dateTime = LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        log.info("{}", dateTime);
    }
}
