package com.zerobase.gardening.encyclopedia.controller;

import com.zerobase.gardening.encyclopedia.dto.DetailResponse;
import com.zerobase.gardening.encyclopedia.dto.GardenListResponse;
import com.zerobase.gardening.encyclopedia.service.ApiService;
import com.zerobase.gardening.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.zerobase.gardening.type.ErrorCode.API_ERROR;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;
    @Value("${api.key}")
    private String apiKey;

    @PostMapping("/api/save")
    public ResponseEntity<?> saveData() {

        // 총 식물의 수 가져오기
        int total = getTotal();
        // total만큼의 관리번호(cntnts) 리스트업
        List<GardenListResponse.Item> cntntsList = getList(total);
        // 관리번호로 식물 상세 정보 불러와서 dto화
        List<DetailResponse.DetailItem> dtoList = getDtoList(cntntsList);

        // dto > entity, DB에 저장
        boolean result = apiService.save(dtoList);
        return ResponseEntity.ok().body(result);
    }

    private int getTotal() {
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

    private List<GardenListResponse.Item> getList(int total) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String url = "http://api.nongsaro.go.kr/service/garden/gardenList?apiKey="
                    + apiKey + "&numOfRows=" + total;
            GardenListResponse response = restTemplate.getForObject(url, GardenListResponse.class);
            List<GardenListResponse.Item> list = response.getBody().getItems().getItemList();
            return list;

        } catch (Exception e) {
            throw new CustomException(API_ERROR);
        }


    }

    private List<DetailResponse.DetailItem> getDtoList(List<GardenListResponse.Item> cntntsList) {
        String urlStr = "http://api.nongsaro.go.kr/service/garden/gardenDtl?apiKey=" +
                apiKey + "&cntntsNo=";
        List<DetailResponse.DetailItem> dtoList = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        for (GardenListResponse.Item cntntsNo : cntntsList) {
            int retry = 0;
            int max = 3;
            boolean success = false;
            while (!success && retry < max) {
                try {
                    DetailResponse detailDto = restTemplate
                            .getForObject(urlStr + cntntsNo.getCntntsNo(), DetailResponse.class);
                    dtoList.add(detailDto.getBody().getItem());
                    success = true;
                } catch (Exception e) {
                    retry++;

                    if (retry == max) {
                        throw new CustomException(API_ERROR);
                    }
                }
            }
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
