package com.example.serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author 吕茂陈
 */
@Getter
@Setter
@Slf4j
public class Person implements Serializable {

	private String name;
	private transient int age;

	public Person(String name, int age) {
		log.info("有参数的构造器");
		this.name = name;
		this.age = age;
	}
}
