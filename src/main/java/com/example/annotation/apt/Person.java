package com.example.annotation.apt;

/**
 * @author 吕茂陈
 */
@Persistent(table = "person_inf")
public class Person {

    @Id(column = "person_id", type = "integer", generator = "identity")
    private int id;

    @Property(column = "person_name", type = "string")
    private String name;

    @Property(column = "person_age", type = "integer")
    private int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
