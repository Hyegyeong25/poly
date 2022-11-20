package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BicycleRowDTO {

    private String rackTotCnt; //거치대 개수
    private String stationName; // 대여소 이름
    private String parkingBikeTotCnt; //자전거 주차 총 건수
    private String shared; //거치율
    private String stationLatitude; //위도
    private String stationLongitude; //경도
    private String stationId; //대여소 ID

}
