package com.example.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/10/17 16:26
 */
@Data
@XmlRootElement(name = "range")
@NoArgsConstructor
@AllArgsConstructor
public class RangeDto {

    private String dnsId;
    private String effectiveScope;
    private String serverId;

    private List<String> strList;
}
