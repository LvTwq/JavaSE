package com.example.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/10/25 17:37
 */
@XmlRootElement(name = "items")
public class ItemsWrapper<T> {

    private List<T> itemList;

    public ItemsWrapper() {}  // JAXB 需要一个无参构造函数

    public ItemsWrapper(List<T> itemList) {
        this.itemList = itemList;
    }

    @XmlElement(name = "item")
    public List<T> getItemList() {
        return itemList;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }
}
