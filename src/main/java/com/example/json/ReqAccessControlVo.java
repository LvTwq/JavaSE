package com.example.json;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2025/6/24 15:27
 */
@Setter
@Getter
public class ReqAccessControlVo {

    private String type;

    private String ip;

    private int action;

    @JsonSetter(nulls = Nulls.SKIP)
    private List<String> weekRange = Lists.newArrayList("1", "2", "3", "4", "5", "6", "7");

    @JsonSetter(nulls = Nulls.SKIP)
    private String startTime = "00:00:00";

    @JsonSetter(nulls = Nulls.SKIP)
    private String entTime = "23:59:59";
}
