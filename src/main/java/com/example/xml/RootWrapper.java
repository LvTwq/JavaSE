package com.example.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/10/25 17:34
 */
@XmlRootElement(name = "root")
public class RootWrapper<T> {
    private List<T> elements;

    @XmlElement(name = "element")
    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
