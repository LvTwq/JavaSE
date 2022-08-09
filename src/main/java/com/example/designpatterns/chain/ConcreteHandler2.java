package com.example.designpatterns.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022-07-13 16:07
 */
@Slf4j
public class ConcreteHandler2 extends Handler {
    public ConcreteHandler2(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.type2) {
            log.info(request.getName() + " is handle by ConcreteHandler2");
            return;
        }
        /*
        这个 successor 是层层嵌套的，这时执行的是 ConcreteHandler1 的 handleRequest() 方法
         */
        if (successor != null) {
            successor.handleRequest(request);
        }
    }
}

