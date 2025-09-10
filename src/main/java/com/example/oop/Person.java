package com.example.oop;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    public String name;
    public int age;

    public Dog dog;

    @JsonSetter(nulls = Nulls.SKIP)
    private String startTime = "00:00:00";

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
