package com.example.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 吕茂陈
 */
public class SimpleDateFormatTest {

    public static void main(String[] args) throws ParseException {
        Date d = new Date();
        // 日期模板字符串
        SimpleDateFormat sdf1 = new SimpleDateFormat("Gyyyy年中第D天");
        String dateStr = sdf1.format(d);
        System.out.println(dateStr);

        String str = "14###3月##21";
        SimpleDateFormat sdf2 = new SimpleDateFormat("y###MMM##d");
        System.out.println(sdf2.parse(str));


        int daysOfThisYear = LocalDate.now().lengthOfYear();
        int i = LocalDate.of(2020, 12, 21).lengthOfYear();
        System.out.println(daysOfThisYear);
        System.out.println(i);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,1,26);
        calendar.add(Calendar.DATE,365);
        System.out.println(calendar);
    }
}
