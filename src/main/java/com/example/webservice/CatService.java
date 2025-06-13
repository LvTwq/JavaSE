package com.example.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/10/15 14:28
 */
@WebService(name = "CatService", // 暴露服务名称
        targetNamespace = "http://catService.service.lvmc.com"// 命名空间,一般是接口的包名倒序
)
public interface CatService {
    @WebMethod
    String message(@WebParam(name = "name") String name);
}