package com.example.designpatterns.chain;

/**
 * @author 吕茂陈
 * @date 2022-07-13 16:08
 */
public class Client {

    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1(null);
        Handler handler2 = new ConcreteHandler2(handler1);
        Handler handler3 = new ConcreteHandler3(handler2);

        /*
               最终都是最后一个 handler 去执行
         */
        Request request1 = new Request(RequestType.type1, "request1");
        handler3.handleRequest(request1);

        /*Request request2 = new Request(RequestType.type2, "request2");
        handler3.handleRequest(request2);

        Request request3 = new Request(RequestType.type3, "request3");
        handler3.handleRequest(request3);*/
    }
}
