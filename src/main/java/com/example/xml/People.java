package com.example.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/10/28 10:32
 */
@Data
@XmlRootElement(name = "people")
public class People {
    private List<Person> person;



    @Data
    @XmlRootElement(name = "person")
    public static class Person {
        private String name;
        private int age;
    }
}
