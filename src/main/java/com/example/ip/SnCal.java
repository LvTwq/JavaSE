package com.example.ip;

import cn.hutool.http.HttpUtil;
import com.example.json.JackJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/9/6 9:32
 */
@Slf4j
public class SnCal {


    private static String ak = "9LW1svPreKk2kELVqGE7Ei6k7y0bYDrq";
    private static String sk = "ROf0hm6Y4uoVPzlcTkpC9NxGhDaBDavT";

/*    public static void main(String[] args) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        SnCal snCal = new SnCal();

// 计算sn跟参数对出现顺序有关，get请求请使用LinkedHashMap保存<key,value>，该方法根据key的插入顺序排序；post请使用TreeMap保存<key,value>，该方法会自动将key按照字母a-z顺序排序。所以get请求可自定义参数顺序（sn参数必须在最后）发送请求，但是post请求必须按照字母a-z顺序填充body（sn参数必须在最后）。以get请求为例：http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。

        Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("ip", "111.206.214.37");
        paramsMap.put("coor", "bd09ll");
        paramsMap.put("ak", ak);

        // 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
        String paramsStr = snCal.toQueryString(paramsMap);

        // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
        String wholeStr = new String("/location/ip?" + paramsStr + sk);

        System.out.println(wholeStr);
        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
        System.out.println(tempStr);

        // 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
        System.out.println(snCal.MD5(tempStr));
    }

    // 对Map内所有value作utf8编码，拼接返回结果
    public String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    // 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }*/

    public static boolean IP_ONLINE_SWITCH = false;

    public static String IP_LOCATION_AK = "9LW1svPreKk2kELVqGE7Ei6k7y0bYDrq";

    public static String IP_LOCATION_SK = "ROf0hm6Y4uoVPzlcTkpC9NxGhDaBDavT";

    // https://api.map.baidu.com/location/ip?ip=10.10.72.118&coor=bd09ll&ak=9LW1svPreKk2kELVqGE7Ei6k7y0bYDrq&sn=39d661bda35d71d143ac9c88b88440c3
    public static String IP_LOCATION_URL = "https://api.map.baidu.com/location/ip?ip=%s&coor=%s&ak=%s&sn=%s";

    private static final Long successStatus = 0L;

    @Test
    public void baiduIp() throws Exception {
        String ip = "20.1.1.2";
        String url = String.format(IP_LOCATION_URL, ip, "bd09ll", IP_LOCATION_AK, calculateSn(ip));
        try {
            String response = HttpUtil.get(url, 800);
            log.info("{}", url);
            log.info("{}", response);
            RespBaiduLocation respBaiduLocation = JackJsonUtil.string2Obj(response, RespBaiduLocation.class);
            if (null != respBaiduLocation && successStatus.equals(respBaiduLocation.getStatus())) {
                log.info("address:{}", respBaiduLocation.getAddress());
            }

        } catch (Exception e) {
            log.error("调用接口异常：{}", url, e);
        }

    }


    private String calculateSn(String ip) {
        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("ip", ip);
        paramsMap.put("coor", "bd09ll");
        paramsMap.put("ak", IP_LOCATION_AK);

        // 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
        String paramsStr = toQueryString(paramsMap);

        // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
        String wholeStr = "/location/ip?" + paramsStr + IP_LOCATION_SK;

        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, StandardCharsets.UTF_8);
        return mD5(tempStr);
    }

    // 对Map内所有value作utf8编码，拼接返回结果
    public String toQueryString(Map<?, ?> data) {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey()).append("=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    StandardCharsets.UTF_8)).append("&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    // 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    public String mD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            log.error("计算md5失败", e);
        }
        return null;
    }
}