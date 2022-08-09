package com.example.designpatterns.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 3033-07-13 16:33
 */
@Slf4j
public class ConcreteHandler3 extends Handler{
    public ConcreteHandler3(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.type3) {
            log.info(request.getName() + " is handle by ConcreteHandler3");
            return;
        }
        /*
        这个 successor 是层层嵌套的，这时执行的是 ConcreteHandler2 的 handleRequest() 方法
         */
        if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
