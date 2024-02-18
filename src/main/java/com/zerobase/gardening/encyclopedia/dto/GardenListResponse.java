package com.zerobase.gardening.encyclopedia.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class GardenListResponse {
    private Body body;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body {
        private Items items;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Items {
        @XmlElement(name = "item")
        private List<Item> itemList;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Item {
        @XmlElement(name = "cntntsNo")
        private String cntntsNo;
    }
}