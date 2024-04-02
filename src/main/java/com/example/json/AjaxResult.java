package com.example.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/1/10 11:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult<T> {

    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String messages;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
