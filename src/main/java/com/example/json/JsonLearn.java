package com.example.json;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/3/23 16:02
 */
public class JsonLearn {



    @Test
    public void test05(){
        JSONObject object = new JSONObject();
        object.putOpt("Device-Info" , "eyJ1c2VyRGV2aWNlTmFtZSI6IkxVSlkiLCJtb2RlbCI6IkxVSlkiLCJmZWF0dXJlQ29kZSI6IjM0ZDNkMDhmZTJiNmNiZWQ5YWEyYTNkMmI2OGE3ODNjIiwic3lzdGVtSW5mbyI6Ik1pY3Jvc29mdCBXaW5kb3dzIDExIFxcdTRlMTNcXHU0ZTFhXFx1NzI0OCIsInRlcm1pbmFsSW5mbyI6IkVuVUVTLXdpbmRvd3MtdjMuMC4wLjAwNDkiLCJ0ZXJtaW5hbFZlcnNpb24iOiJVRVMgMy4wLjAuMDA0OSIsImxvY2FsQWRkciI6IjE5Mi4xNjguMTAxLjExMCIsImxvY2FsTWFjIjoiRTQ6NTQ6RTg6QTg6REE6NkIiLCJjb250cm9sbGVyUG9ydCI6IiIsImRldmljZU1vZGVsIjoiVm9zdHJvIDM2NzAiLCJtYWNBZGRyZXNzIjoiRTQ6NTQ6RTg6QTg6REE6NkIiLCJzZXJpYWxOdW1iZXIiOiJCMlE2VFkyIiwibWFudUZhY3R1cmVyIjoiRGVsbCBJbmMuIiwibmV0d29ya1R5cGUiOiIifQ==");
//        JSONArray jsonArray = object.getJSONArray("Device-Info");
//        jsonArray.get(0);
    }


    @Test
    public void test01() throws IOException {
//        String str = "{\"foo\": \"bar\nbaz\"}";
//        String str = "{\"foo\": \"bar\tbaz\"}";
        String str = "{\"foo\": \"bar\rbaz\"}";
//        Map bean = JSONUtil.toBean(str, Map.class);
//        System.out.println(bean);
        ObjectMapper objectMapper = new ObjectMapper();
        Map value = objectMapper.readValue(str, new TypeReference<>() {
        });
        System.out.println(value);
    }

    private static final String WJ = "spFilePath/terminal.json";

    @Test
    public void test02() throws IOException {
//        String str = "{\n" +
//                "    \"detail\": [\n" +
//                "        {\n" +
//                "            \"content\": null,\n" +
//                "            \"name\": \"network\"\n" +
//                "        }\n" +
//                "    ],\n" +
//                "    \"eventType\": \"all\"\n" +
//                "}";
        String str = Files.readString(Paths.get(WJ));
        RespMqttTerminal respMqttTerminal = JackJsonUtil.string2Obj(str, new TypeReference<>() {
                });
//        System.out.printf(respMqttTerminal.toString());
    }

}
