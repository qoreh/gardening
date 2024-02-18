package com.zerobase.gardening.encyclopedia.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetailResponse {
    private DetailBody body;


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)

    public static class DetailBody {
        private DetailItem item;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DetailItem {
        @XmlElement(name = "plntbneNm")
        private String scientificName;

        @XmlElement(name = "plntzrNm")
        private String englishName;

        @XmlElement(name = "distbNm")
        private String tradeName;

        @XmlElement(name = "fmlCodeNm")
        private String familyName;

        @XmlElement(name = "orgplceInfo")
        private String nativeHabitat;

        @XmlElement(name = "managelevelCodeNm")
        private String manageLevel;

        @XmlElement(name = "managedemanddoCodeNm")
        private String manageDemandLevel;

        @XmlElement(name = "postngplaceCodeNm")
        private String place;

        @XmlElement(name = "ignSeasonCodeNm")
        private String bloomingSeason;

        @XmlElement(name = "flclrCodeNm")
        private String flowerColor;

        @XmlElement(name = "lefStleInfo")
        private String leafShape;

        @XmlElement(name = "lefcolrCodeNm")
        private String leafColor;

        @XmlElement(name = "prpgtmthCodeNm")
        private String propagation;

        @XmlElement(name = "lighttdemanddoCodeNm")
        private String lightDemandLevel;

        @XmlElement(name = "dlthtsCodeNm")
        private String diseaseAndPest;

        @XmlElement(name = "grwtveCodeNm")
        private String growthVelocity;

        @XmlElement(name = "growthHgInfo")
        private String growthHeight;

        @XmlElement(name = "growthAraInfo")
        private String growthWidth;

        @XmlElement(name = "grwhTpCodeNm")
        private String growthTemperature;

        @XmlElement(name = "winterLwetTpCodeNm")
        private String winterLowestTemperature;

        @XmlElement(name = "hdCodeNm")
        private String humidity;

        @XmlElement(name = "frtlzrInfo")
        private String fertilizer;

        @XmlElement(name = "soilInfo")
        private String soil;

        @XmlElement(name = "watercycleSprngCodeNm")
        private String springWateringCycle;

        @XmlElement(name = "watercycleSummerCodeNm")
        private String summerWateringCycle;

        @XmlElement(name = "watercycleAutumnCodeNm")
        private String fallWateringCycle;

        @XmlElement(name = "watercycleWinterCodeNm")
        private String winterWateringCycle;

        @XmlElement(name = "speclmanageInfo")
        private String etc;
    }
}