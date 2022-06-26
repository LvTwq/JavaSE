package com.example.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class StringTest {

    public static void main(String[] args) {
        String s = "xyz";
        String s3 = getString().intern();
        String s1 = new String("xyz");
        String s2 = new String("xyz");
        log.info("s1和s是否相等：{}", s1.equals(s));
        log.info("s1和s是否同一对象：{}", s == s1);
        log.info("s1和s2是否同一对象：{}", s2 == s1);
    }


    private static String getString() {
        return "xyz";
    }


    @Test
    public void test01() {
//        String str = "https://cloud.xylink.com/api/rest/external/v1/vods/9308673/getdownloadurl?enterpriseId=50c76142608a6f8d4d1a592f212d3b15165e01a6&signature=fX5PYU8wzcAef7cNQe6pV2LkMz00WAdZOnHnt1CJpE0%3D";
        String str = "http://101.34.231.204:8888/testimony/api/v1/file/xxxxxxxxxxxxxx/download?type=1";
        String[] split = str.split("testimony", 0);
        for (String s : split) {
            log.info(s);
        }
    }

    @Test
    public void test03() {
        String[] split = StringUtils.split("安徽省-合肥市-瑶海区", "-");
        log.info(split[split.length - 1]);
    }

    @Test
    public void test02() {
        String str = "aaaaa,bbbbb,ccccccc";
        log.info(str.substring(str.indexOf(",") + 1));
    }


    @Test
    public void test04() {
        Integer i = null;
        log.info("{}", i.equals("1"));
    }


    @Test
    public void test05() {
        // true
        log.info("{}", null == null);
        Integer i = null;
        // true
        log.info("{}", i == null);
    }

    @Test
    public void test06() {
        int i1 = 1;
        Integer i2 = 1;
        String s = "1";
        log.info("{}", ObjectUtil.equals(i1, i2));
    }


    private static final String s = "11234";

    @Test
    public void test07() {
        String s = "Test";
        String s1 = s.toUpperCase();
        log.info("{}", s);
        log.info("{}", s1);
    }


    @Test
    public void test08() {
        System.out.println(1 + 2 + "," + 3 + 4);
    }

}
