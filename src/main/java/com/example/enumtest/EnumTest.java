package com.example.enumtest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static com.example.enumtest.GateWayModeEnum.default_by_pass;

/**
 * @author 吕茂陈
 */
@Slf4j
public class EnumTest {

    public void judge(SeasonEnum s) {
        switch (s) {
            case SPRING:
                System.out.println("春天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case FALL:
                System.out.println("秋天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        // 枚举类默认有一个values()方法，返回该枚举类的所有实例
        for (SeasonEnum s :
                SeasonEnum.values()) {
           log.info("{}",s);
        }
        new EnumTest().judge(SeasonEnum.SPRING);
        log.info("{}",GroupByCol.valueOf("TARGET"));
    }


    @Test
    public void test01() {
        String s = String.valueOf(default_by_pass);
        System.out.println(s);
    }


}
