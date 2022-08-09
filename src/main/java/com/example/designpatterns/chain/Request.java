package com.example.designpatterns.chain;

/**
 * @author 吕茂陈
 * @date 2022-07-13 16:08
 */
public class Request {
    private RequestType type;
    private String name;

    public Request(RequestType type, String name) {
        this.type = type;
        this.name = name;
    }

    public RequestType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

