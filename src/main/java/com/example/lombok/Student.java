package com.example.lombok;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
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
