package com.example.serializable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class Teacher implements Serializable {
    private String name;
    private Person student;

}
