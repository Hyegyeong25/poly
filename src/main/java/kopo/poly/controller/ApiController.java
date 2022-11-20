package kopo.poly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.dto.BicycleDTO;
import kopo.poly.dto.BicycleRowDTO;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ApiController {
    @GetMapping("/api")
    public String callApi() throws Exception {
        log.info(this.getClass().getName()+".callApi Start!!");

        StringBuilder sb = new StringBuilder();

        String[] start = {"1", "1001", "2001"};
        String[] finish = {"1000", "2000", "3000"};

        //for (int i = 0; i < 3; i++) {


        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" +  URLEncoder.encode("597a527a7168796537374a76444a58","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("bikeList","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("9","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("9","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

       // }

//        Map<String, Object> rMap = new ObjectMapper().readValue(sb.toString(), LinkedHashMap.class);
//        Map<String, Object> stationInfo = (Map<String, Object>) rMap.get("stationInfo");
//        //System.out.println(sb.toString());
//
//        //Map<String, String> test = (Map<String, String>) rMap.get("0");
//
//
//        //String name = test.get("STA_LOC");
//        //log.info("name test: "+name);
//
//        log.info("stationInfo : "+stationInfo);
//
//        List<Map<String, Object>> rowList = (List<Map<String, Object>>) stationInfo.get("row");
//
//        List<BicycleRowDTO> pList = new LinkedList<>();
//
//        for (Map<String, Object> rowMap : rowList) {
//            String sta_loc = CmmUtil.nvl((String) rowMap.get("STA_LOC"));
//            String rent_nm = CmmUtil.nvl((String) rowMap.get("RENT_NM"));
//            String sta_lat = CmmUtil.nvl((String) rowMap.get("STA_LAT"));
//            String sta_long = CmmUtil.nvl((String) rowMap.get("STA_LONG"));
//
//            log.info("------------------------------");
//            log.info("sta_loc: "+sta_loc);
//            log.info("rent_nm: "+rent_nm);
//            log.info("sta_lat: "+sta_lat);
//            log.info("sta_long: "+sta_long);
//            log.info("------------------------------");
//
//            BicycleRowDTO bDTO = new BicycleRowDTO();
//
//            bDTO.setSta_loc(sta_loc);
//            bDTO.setRent_nm(rent_nm);
//            bDTO.setSta_lat(sta_lat);
//            bDTO.setSta_long(sta_long);
//
//            pList.add(bDTO);
//            bDTO = null;
//        }
//
//        BicycleDTO biDTO = new BicycleDTO();
//
//        biDTO.setRowList(pList);

        log.info(this.getClass().getName()+".callBicycleApi End!!");
        return sb.toString();
    }
}
