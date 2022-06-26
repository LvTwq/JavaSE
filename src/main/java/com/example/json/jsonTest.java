package com.example.json;

import com.alibaba.fastjson.JSON;

import cn.hutool.json.JSONUtil;

public class jsonTest {

    public static void main(String[] args) {

        /* json字符串转化为对象
         *
         * 故意在Json字符串中多了一个telephone，少了一个Staff中的birthday
         * */
        String jsonString = "{name:'Antony',age:'12',sex:'male',telephone:'88888'}";
        Staff staff = JSON.parseObject(jsonString, Staff.class);
        System.out.println(staff.toString());

        /* 对象转化为json字符串 */
        String s = JSON.toJSONString(staff);
        System.out.println(s);


        Object name = JSONUtil.getByPath(JSONUtil.parse("wjsedfikgvljmdlfkg"), "name");
        System.out.println(name);
    }


}
