package com.example.lombok;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class GetAndSet {

    @JSONField(serialize = false)
    @Getter(onMethod_ = @JsonIgnore)
    private List<String> cBhList;

    @JsonProperty("bh")
    private String bh;

    @Getter(onMethod_ = { @JsonProperty("cBh") })
    private String cBh;


    private String Name;
    private String uName;
    private String name;

    public String getName() {
        return Name;
    }
}
