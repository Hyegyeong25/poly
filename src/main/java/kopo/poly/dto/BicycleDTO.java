package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BicycleDTO {

    // 내 위도 경도 받아올 변수
    private String lat; //경도
    private String lon; //위도
    
    private List<BicycleRowDTO> bicycleList;
}
