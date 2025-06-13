package com.example.xml;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/10/28 10:32
 */
public class JacksonXmlTest {


    public static void main(String[] args) throws IOException {
        People.Person p1 = new People.Person();
        p1.setName("张三");
        p1.setAge(30);

        People.Person p2 = new People.Person();
        p2.setName("李四");
        p2.setAge(25);

        List<People.Person> persons = Arrays.asList(p1, p2);
        People people = new People();
        people.setPerson(persons);

//        String xml = JacksonXmlUtil.toXml(persons);
//        System.out.println(xml);
        String string = JAXBUtil.listBean2Str(persons);
        System.out.println(string);

        // 反序列化
//        People deserializedPeople = JacksonXmlUtil.fromXml(xml, People.class);
//        System.out.println(deserializedPeople.getPerson());
    }
}
