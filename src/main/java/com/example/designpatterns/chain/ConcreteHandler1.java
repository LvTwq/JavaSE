package com.example.designpatterns.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022-07-13 16:07
 */
@Slf4j
public class ConcreteHandler1 extends Handler {
    public ConcreteHandler1(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.type1) {
            log.info(request.getName() + " is handle by ConcreteHandler1");
            return;
        }

        if (successor != null) {
            successor.handleRequest(request);
        }
    }
}

