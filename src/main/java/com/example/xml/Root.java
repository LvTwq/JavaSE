package com.example.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/10/25 17:09
 */
@Data
@XmlRootElement(name = "root")
public class Root<T> {

    @XmlElement(name = "item")
    private List<T> items;


}