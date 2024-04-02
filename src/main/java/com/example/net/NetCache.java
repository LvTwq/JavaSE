package com.example.net;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Security;

/**
 * @author 吕茂陈
 * @description
 * @date 2022/11/16 11:28
 */
@Slf4j
public class NetCache {


    /**
     * 解析并打印 IP
     */
    private static void printIp(String host) {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(host);

            log.info("IP：{}，域名：{}", address.getHostAddress(), address.getHostName());
            log.info("sun.net.inetaddr.ttl:{}", System.getProperty("sun.net.inetaddr.ttl"));
            log.info("sun.net.inetaddr.negative.ttl:{}", System.getProperty("sun.net.inetaddr.negative.ttl"));
            log.info("networkaddress.cache.ttl:{}", Security.getProperty("networkaddress.cache.ttl"));
            log.info("networkaddress.cache.negative.ttl:{}", Security.getProperty("networkaddress.cache.negative.ttl"));
        } catch (UnknownHostException e) {
            log.error("解析异常", e);
        }

    }

    public static void main(String[] args) {
        while (true) {
            printIp("historymuseum.lvmc.top");

            try {
                Thread.sleep(1000);
                log.info("休眠一秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
