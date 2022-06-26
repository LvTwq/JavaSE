package com.example.lombok;

import javax.validation.constraints.NotNull;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Student {
    private Long id;
    @NonNull
    private String phone;
    @NotNull
    private Integer status = 0;
    private final Integer age;
    private final String country = "china";
}
