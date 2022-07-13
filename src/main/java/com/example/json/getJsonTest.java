package com.example.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author 吕茂陈
 */
public class getJsonTest {

    public static void main(String[] args) {
        testJson();
        testJson2();

        test();
    }

    /**
     * JSON 对象与字符串的相互转化
     */
    public static void test() {
        // 从字符串解析JSON对象
        JSONObject obj = JSON.parseObject("{\"runoob\":\"菜鸟教程\"}");
        System.out.println(obj);
        // 从字符串解析JSON数组
        JSONArray arr = JSON.parseArray("[\"菜鸟教程\",\"RUNOOB\"]\n");
        System.out.println(arr);
        // 将JSON对象转化为字符串
        String objStr = JSON.toJSONString(obj);
        System.out.println(objStr);
        // 将JSON数组转化为字符串
        String arrStr = JSON.toJSONString(arr);
        System.out.println(arrStr);
    }

    /**
     * 编码 从 Java 变量到 JSON 格式的
     */
    public static void testJson() {
        // 首先建立一个JSON对象，然后依次添加字符串、整数、布尔值以及数组，最后将其打印为字符串。
        JSONObject object = new JSONObject();
        object.put("string", "qwertyuiop");

        object.put("int", 2);

        object.put("boolean", true);

        List<Integer> integers = Arrays.asList(1, 2, 3);
        object.put("list", integers);

        object.put("null", null);

        System.out.println(object);
    }

    /**
     * 解码
     * <p>
     * 从 JSON 对象到 Java 变量
     */
    public static void testJson2() {
        // 首先从 JSON 格式的字符串中构造一个 JSON 对象，之后依次读取字符串、整数、布尔值以及数组，最后分别打印
        JSONObject object =
                JSON.parseObject(
                        "{\"boolean\":true,\"string\":\"qwertyuiop\",\"list\":[1,2,3],\"int\":2}");
        String string = object.getString("string");
        System.out.println(string);

        int i = object.getIntValue("int");
        System.out.println(i);

        boolean b = object.getBooleanValue("boolean");
        System.out.println(b);

        List<Integer> integerList =
                JSON.parseArray(object.getJSONArray("list").toString(), Integer.class);
        System.out.println(integerList);

        System.out.println(object.getString("null"));
    }


    @Test
    public void test01() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", null);
        // null
        Integer code = jsonObject.getInteger("code");
        System.out.println(code);
        // 0
        int code1 = jsonObject.getIntValue("code");
        System.out.println(code1);
        // NullPointerException
        int code2 = jsonObject.getInteger("code");
        System.out.println(code2);
        // 0
        Integer code3 = jsonObject.getIntValue("code");
        System.out.println(code3);
    }


    @Test
    public void test02() {
        String str = "{\n" +
                "    \"code\": 200,\n" +
                "    \"msg\": \"success!\",\n" +
                "    \"data\": []\n" +
                "}";
        JSONObject jo = JSON.parseObject(str);
        JSONArray data = jo.getJSONArray("data");
        System.out.println(data.isEmpty());
    }
}
