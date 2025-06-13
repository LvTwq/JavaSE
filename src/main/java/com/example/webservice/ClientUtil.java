package com.example.webservice;

import cn.hutool.http.HttpRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/10/15 14:39
 */
@Slf4j
public class ClientUtil {


    @Test
    public void restTemplateActiveUsers(){
        //构造webservice请求参数
        StringBuffer soapRequestData = new StringBuffer();
        soapRequestData.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:hel=\"http://hello.server.webservice.huzhihui.com\">");
        soapRequestData.append("<soap:Header/>");
        soapRequestData.append("<soap:Body>");

        soapRequestData.append("<hel:activeUsers>");
        soapRequestData.append("<userDtos>");
        soapRequestData.append("<active></active>");
        soapRequestData.append("<id>1</id>");
        soapRequestData.append("<userName>hzh</userName>");
        soapRequestData.append("</userDtos>");
        soapRequestData.append("</hel:activeUsers>");

        soapRequestData.append("</soap:Body>");
        soapRequestData.append("</soap:Envelope>");

        String body = HttpRequest.post("http://localhost:8080/webService/helloService?wsdl")
                .body(soapRequestData.toString())
                .header("Content-Type", "application/xml")
                .execute()
                .body();
        System.out.println(body);
    }


    @Data
    static class UserDto {

        private Long id;

        private String userName;

        private Boolean active;
    }

    @Test
    public void test04() throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/webService/helloService?wsdl");

        Object[] objects = client.invoke("hi", "zhangsan");//hi方法名 后面是可变参数
        //输出调用结果
        System.out.println(objects[0].getClass());
        System.out.println(objects[0].toString());


    }

    @Test
    public void activeUsers() throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/webService/helloService?wsdl");

        List<Object> userDtos = new ArrayList<>();
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUserName("zs");

        userDtos.add(userDto);
        Object[] objects = client.invoke("activeUsers", userDtos);//list3方法名 后面是可变参数
        //输出调用结果
        System.out.println(objects[0].getClass());
        System.out.println(objects[0].toString());
    }


    public static String callWebSV(String wsdUrl, String operationName, String... params) throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Object[] objects;
        try (Client client = dcf.createClient(wsdUrl)) {
            //client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke(operationName, params);
        }
        return objects[0].toString();
    }

    //在一个方法中连续调用多次WebService接口，每次调用前需要重置上下文。
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    @Test
    public void getMessage() {
        log.info("======开始调用webservice接口=====");
        String url = "http://localhost:8080/webService/CatService?wsdl";
        String methodName = "message";
        log.info("Calling {}", url);
        String result = "";
        try {
            result = callWebSV(url, methodName, "name");
            log.info("===Finished!===恭喜你啊！！！CatService接口调用成功！！！===获得的数据是：{}", result);
        } catch (Exception e) {
            log.error("接口调用失败！！！！");
        }

    }

    @Test
    public void getMessage2() {
        log.info("======开始调用webservice接口=====");
        String url = "http://localhost:8080/webService/TestService?wsdl";
        String methodName = "sendMessage";
        log.info("Calling" + url);
        String result = "";
        try {
            result = callWebSV(url, methodName, "username");
            log.info("===Finished!===恭喜你啊！！！TestService接口调用成功！！！===获得的数据是：{}", result);
        } catch (Exception e) {
            log.error("接口调用失败！！！！");
        }

    }


    @Test
    public void test01() throws MalformedURLException {
        //创建WSDL文件的URL，这个参数为暴露webervice的网址
        URL wsdlLocation = new URL("http://localhost:8080/webService/CatService?wsdl");
        //创建服务名称：第一个参数为命名空间地址，第二个参数 为本地部分的命名 HelloWebServiceImplService  这个是对应的实现类名加上Service
        QName serviceName = new QName("http://catService.service.lvmc.com", "CatServiceImplService");
        Service service = Service.create(wsdlLocation, serviceName);

        //获取服务实现类  参数为对应的服务接口.class
        CatService port = service.getPort(CatService.class);
        //调用方法
        String asd = port.message("lvmc");
    }


    @Test
    public void test02() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/xml; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://localhost:8080/webService/CatService?wsdl")
                .get()
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/xml")
                .addHeader("Accept", "*/*")
                .addHeader("Host", "localhost:8080")
                .addHeader("Connection", "keep-alive")
                .build();
        Response response = client.newCall(request).execute();
        String string = response.body().string();
        response.close();
        log.info("response:{}", response);
        log.info("string:{}", string);
    }
}
