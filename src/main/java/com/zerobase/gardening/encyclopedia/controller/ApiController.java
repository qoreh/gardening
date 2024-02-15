package com.zerobase.gardening.encyclopedia.controller;

import com.zerobase.gardening.encyclopedia.dto.PlantDetailDto;
import com.zerobase.gardening.encyclopedia.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;
    @PostMapping("/api/save")
    public ResponseEntity<?> saveData() {
        String apiKey = "20240206CEALQSMDGKEMGAKWYTOULQ";

        // 총 식물의 수 가져오기
        int total = getTotal();
        // total만큼의 관리번호(cntnts) 리스트업
        ArrayList<String> cntntsList = getList(apiKey, total);
        // 관리번호로 식물 상세 정보 불러와서 dto화
        ArrayList<PlantDetailDto> dtoList = getDtoList(apiKey, cntntsList);

        // dto > entity, DB에 저장
        boolean result = apiService.save(dtoList);
        return ResponseEntity.ok().body(result);
    }

    private int getTotal() {
        String apiKey = "20240206CEALQSMDGKEMGAKWYTOULQ";
        int result = 0;

        try {
            URL url = new URL("http://api.nongsaro.go.kr/service/garden/gardenList?apiKey="
                    + apiKey);

            Document document = getDocument(url);

            Element root = document.getRootElement();
            Element body = root.getChild("body");
            Element items = body.getChild("items");
            Element totalCount = items.getChild("totalCount");
            result = Integer.parseInt(totalCount.getValue());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private ArrayList<String> getList(String apiKey, int total) {

        ArrayList<String> cntList = new ArrayList<>();

        try {
            URL url = new URL("http://api.nongsaro.go.kr/service/garden/gardenList?apiKey="
                    + apiKey + "&numOfRows=" + total);

            Document document = getDocument(url);

            Element root = document.getRootElement();
            Element body = root.getChild("body");
            Element items = body.getChild("items");
            List<Element> item = items.getChildren("item");

            for (Element object : item) {
                Element cntntsNo = object.getChild("cntntsNo");
                cntList.add(cntntsNo.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cntList;
    }

    private ArrayList<PlantDetailDto> getDtoList(String apiKey, ArrayList<String> cntntsList) {
        String urlStr = "http://api.nongsaro.go.kr/service/garden/gardenDtl?apiKey=" +
                apiKey + "&cntntsNo=";
        ArrayList<PlantDetailDto> dtoList = new ArrayList<>();

        try {
            for (String cntntsNo : cntntsList) {

                URL url = new URL(urlStr + cntntsNo);
                Document document = getDocument(url);

                Element root = document.getRootElement();
                Element body = root.getChild("body");
                Element item = body.getChild("item");

                PlantDetailDto detailDto = PlantDetailDto.builder()
                        .scientificName(item.getChild("plntbneNm").getValue())
                        .englishName(item.getChild("plntzrNm").getValue())
                        .tradeName(Optional.ofNullable(item.getChild("distbNm").getValue()).orElse(""))
                        .familyName(item.getChild("fmlCodeNm").getValue())
                        .nativeHabitat(Optional.ofNullable(item.getChild("orgplceInfo").getValue()).orElse(""))
                        .manageLevel(item.getChild("managelevelCodeNm").getValue())
                        .manageDemandLevel(item.getChild("managedemanddoCodeNm").getValue())
                        .place(Optional.ofNullable(item.getChild("postngplaceCodeNm").getValue()).orElse(""))
                        .bloomingSeason(Optional.ofNullable(item.getChild("ignSeasonCodeNm").getValue()).orElse(""))
                        .flowerColor(Optional.ofNullable(item.getChild("flclrCodeNm").getValue()).orElse(""))
                        .leafShape(item.getChild("lefStleInfo").getValue())
                        .leafColor(item.getChild("lefcolrCodeNm").getValue())
                        .propagation(Optional.ofNullable(item.getChild("prpgtmthCodeNm").getValue()).orElse(""))
                        .lightDemandLevel(item.getChild("lighttdemanddoCodeNm").getValue())
                        .diseaseAndPest(Optional.ofNullable(item.getChild("dlthtsCodeNm").getValue()).orElse(""))
                        .growthVelocity(Optional.ofNullable(item.getChild("grwtveCodeNm").getValue()).orElse(""))
                        .growthHeight(Optional.ofNullable(item.getChild("growthHgInfo").getValue()).orElse(""))
                        .growthWidth(Optional.ofNullable(item.getChild("growthAraInfo").getValue()).orElse(""))
                        .growthTemperature(item.getChild("grwhTpCodeNm").getValue())
                        .winterLowestTemperature(item.getChild("winterLwetTpCodeNm").getValue())
                        .humidity(item.getChild("hdCodeNm").getValue())
                        .fertilizer(Optional.ofNullable(item.getChild("frtlzrInfo").getValue()).orElse(""))
                        .soil(Optional.ofNullable(item.getChild("soilInfo").getValue()).orElse(""))
                        .springWateringCycle(item.getChild("watercycleSprngCodeNm").getValue())
                        .summerWateringCycle(item.getChild("watercycleSummerCodeNm").getValue())
                        .fallWateringCycle(item.getChild("watercycleAutumnCodeNm").getValue())
                        .winterWateringCycle(item.getChild("watercycleWinterCodeNm").getValue())
                        .etc(Optional.ofNullable(item.getChild("speclmanageInfo").getValue()).orElse(""))
                        .build();

                dtoList.add(detailDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dtoList;

    }

    private Document getDocument(URL url) {
        int retryCount = 0;
        int max = 3;

        while (retryCount < max) {
            try {

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                SAXBuilder builder = new SAXBuilder();

                return builder.build(urlConnection.getInputStream());
            } catch (IOException | JDOMException e) {

                if (e instanceof IOException && ((IOException) e).getMessage().contains("502")) {
                    retryCount++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                } else {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

}
