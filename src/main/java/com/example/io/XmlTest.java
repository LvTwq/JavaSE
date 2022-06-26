package com.example.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.springframework.util.StringUtils;

import com.example.io.bean.FyajVo;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2021/12/20 16:40
 */
@Slf4j
public class XmlTest {

    @Test
    public void test01() {

        Document doc = null;
        SAXReader saxReader = new SAXReader();
        try (InputStream inputStream = XmlTest.class.getResourceAsStream("/wsdl.txt")) {
            doc = saxReader.read(inputStream);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        Element element = doc.getRootElement();
        element.content();
    }


    @Test
    public void test02() {
        String str = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "    <soap:Body>\n" +
                "        <qryMPSqlByKeyResponse xmlns=\"http://service.fgw.bm.com/\">\n" +
                "            <String>\n" +
                "        {\"status\":200," +
                "\"msg\":\"ok\"," +
                "\"data\":[{\"rn\":9353,\"yf\":\"7\",\"dqdm\":\"3203\",\"dq\":\"徐州市\",\"ah\":\"(2021)苏8601行初923号\",\"fydm\":\"320350\",\"fymc\":\"徐州铁路运输法院\",\"larq\":\"20210716\",\"ajlymc\":\"当事人起诉\",\"spcxmc\":\"一审\",\"ay\":\"尚无法律法规明确名称的可诉的行政行为/事项\",\"jarq\":null,\"jafsmc\":null,\"jasymc\":null,\"yg\":\"张佩佩,胡立伟,阚稳,陆红华,林逍,王秀云,闫莎莎,薛印海\",\"bg\":\"徐州市自然资源和规划局\",\"fygtbg\":null,\"dsr\":\"姜秀云,钱晓梅,翟景春,姜文\",\"sfkt\":\"否\",\"xzjgsfct\":\"否\",\"xzxwzl\":\"行政登记\",\"sfsjwjsc\":\"否\",\"xzjgsfbs\":\"否\",\"bsyy\":null,\"sfss\":\"否\",\"ysah\":null,\"sffg\":\"否\",\"esah\":null,\"esjg\":null,\"gply\":null,\"updateTime\":\"2021-10-27 10:15:37\"},{\"rn\":18975,\"yf\":\"10\",\"dqdm\":\"3201\",\"dq\":\"南京市\",\"ah\":\"(2021)苏01行赔终71号\",\"fydm\":\"320100\",\"fymc\":\"江苏省南京市中级人民法院\",\"larq\":\"20211019\",\"ajlymc\":\"当事人上诉\",\"spcxmc\":\"二审\",\"ay\":\"行政赔偿\",\"jarq\":null,\"jafsmc\":null,\"jasymc\":null,\"yg\":\"张滢\",\"bg\":\"南京市秦淮区人民政府瑞金路街道办事处\",\"fygtbg\":null,\"dsr\":null,\"sfkt\":\"否\",\"xzjgsfct\":\"否\",\"xzxwzl\":null,\"sfsjwjsc\":\"否\",\"xzjgsfbs\":\"否\",\"bsyy\":null,\"sfss\":\"否\",\"ysah\":\"(2021)苏0192行赔初32号\",\"sffg\":\"否\",\"esah\":null,\"esjg\":null,\"gply\":null,\"updateTime\":\"2021-10-27 10:00:58\"}]" +
                "}\n" +
                "\n" +
                "    </String>\n" +
                "        </qryMPSqlByKeyResponse>\n" +
                "    </soap:Body>" +
                "</soap:Envelope>";
        JSONObject jsonObject = JSONUtil.parseFromXml(ResourceUtil.readUtf8Str("wsdl.txt"));
        JSONObject enveElement = (JSONObject) jsonObject.get("soap:Envelope");
        JSONObject bodyElement = (JSONObject) enveElement.get("soap:Body");
        JSONObject resElement = (JSONObject) bodyElement.get("qryMPSqlByKeyResponse");

        JSONArray jsonArray = JSONUtil.parseArray(JSONUtil.parse(resElement.get("String")).getByPath("data"));
        JSONArray array = new JSONArray();
        List<FyajVo> fyajVos = JSONUtil.toList(jsonArray, FyajVo.class);
        log.info(String.valueOf(fyajVos));
    }

    @Test
    public void test03() {
        String s1 = "null";
        String s2 = "张三";
        String s3 = "张三";
        System.out.println(StrUtil.concat(true, s1, StringUtils.isEmpty(s1) ? null : ",", s2, s3));
    }

    @Test
    public void test04() {
        System.out.println("111".equals(null));
    }
}

