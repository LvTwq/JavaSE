package com.example.optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 吕茂陈
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer id;
    private String name;
    private Integer age;
    private Double salary;
}

