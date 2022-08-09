package com.example.designpatterns.chain;

/**
 * 定义处理请求的接口，它的子类都是后继链（successor）
 *
 * @author 吕茂陈
 * @date 2022-07-13 16:07
 */
public abstract class Handler {
    protected Handler successor;

    public Handler(Handler successor) {
        this.successor = successor;
    }

    protected abstract void handleRequest(Request request);
}

