package com.example.json;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 吕茂陈
 */
@Getter
@Setter
public class Staff {
    private String name;
    private Integer age;
    private String sex;
    private Date birthday;

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
