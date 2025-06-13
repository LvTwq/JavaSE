package com.example.json;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/3/16 16:05
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RespMqttTerminal {


    private String eventType;

    private List<Detail> detail;


    @Data
    @Builder
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {
        private String name;
        /**
         * json数组
         */
        private List<JSONObject> content;
    }
}
