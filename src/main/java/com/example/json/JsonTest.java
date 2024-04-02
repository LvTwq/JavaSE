package com.example.json;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 吕茂陈
 */
@Slf4j
public class JsonTest {

    @Test
    public void test06() {

        /* json字符串转化为对象
         *
         * 故意在Json字符串中多了一个telephone，少了一个Staff中的birthday
         * */
        String jsonString = "{name:'Antony',age:'12',sex:'male',telephone:'88888'}";
        Staff staff = JSON.parseObject(jsonString, Staff.class);
        log.info("{}",staff.toString());

        /* 对象转化为json字符串 */
        String s = JSON.toJSONString(staff);
        log.info("{}",s);


        Object name = JSONUtil.getByPath(JSONUtil.parse("wjsedfikgvljmdlfkg"), "name");
        log.info("{}",name);
    }


    @Test
    public void test05() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AjaxResult o = objectMapper.readValue("{\"code\":\"200\",\"messages\":\"OK\",\"data\":true}", new TypeReference<>() {
            });
            log.info("{}", o);
        } catch (IOException e) {
            log.error("", e);
        }
    }

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


    @Test
    public void test03(){
        LogCollectModel policyLogModel = LogCollectModel.builder()
                .requestSource("enclient-202302061434445491622483916104093696-hctpjg4fxj6m1lia")
                .operation("userLogin.event.policy")
                .policies(JSONUtil.toJsonStr(List.of(PolicyLogCollect.builder().targetPolicyName("策略1").policyResultDes("passed").build())))
                .build();
        log.info(JSONUtil.toJsonStr(policyLogModel));
        LogCollectModel policyLogModel1 = LogCollectModel.builder()
                .requestSource("enclient-202302061434445491622483916104093696-hctpjg4fxj6m1lia")
                .operation("userLogin.event.policy")
                .policie(List.of(PolicyLogCollect.builder().targetPolicyName("策略1").policyResultDes("passed").build()))
                .build();
        log.info(JSONUtil.toJsonStr(policyLogModel1));
    }


    @Test
    public void test04(){
        String str = "[{\"targetPolicyName\":\"策略1\",\"policyResultDes\":\"passed\"}]";
        String str1 = "[{\"targetPolicyName\":\"策略2\",\"policyResultDes\":\"passed\"}]";
        List<PolicyLogCollect> policyLogCollects = JSONUtil.toList(str, PolicyLogCollect.class);
        log.info("{}", policyLogCollects);

        List<PolicyLogCollect> collect = Stream.of(str, str1).map(e -> JSONUtil.toList(e, PolicyLogCollect.class))
                .flatMap(Collection::stream).collect(Collectors.toList());
        log.info("{}", collect);
    }



}
