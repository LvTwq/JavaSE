package com.example.net;

import cn.hutool.core.net.Ipv4Util;
import org.junit.Test;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/3/10 9:41
 */
public class IpTest {

    @Test
    public void test01() {
        String str = "255.255.0.0";
        System.out.println(netmask2Prefix(str));
        System.out.println(Ipv4Util.getMaskBitByMask(str));

        String str1 = "64";
        System.out.println(netmask2Prefix(str1));
        System.out.println(Ipv4Util.getMaskBitByMask(str1));
    }



    private String netmask2Prefix(String netmask) {
        String[] data = netmask.split("\\.");
        int len = 0;

        for (String n : data) {
            len += (8 - Math.log(256 - Integer.parseInt(n)) / Math.log(2));
        }

        return String.valueOf(len);
    }
}
