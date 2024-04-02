package com.example.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/7/13 14:45
 */
public class JackJsonTest {

    public static void main(String[] args) throws Exception {
        String jsonString = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        // JsonNode 是一个抽象类，具体的实现类包括 ObjectNode（表示 JSON 对象）和 ArrayNode（表示 JSON 数组）
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        String name = jsonNode.get("name").asText();
        int age = jsonNode.get("age").asInt();
        String city = jsonNode.get("city").asText();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        System.out.println("==========================");

        JsonNode jsonNode1 = JackJsonUtil.string2Obj(jsonString, JsonNode.class);
        System.out.println("Name: " + jsonNode1.get("name"));
        System.out.println("Age: " + jsonNode1.get("age"));
        System.out.println("City: " + jsonNode1.get("city"));
    }

}
