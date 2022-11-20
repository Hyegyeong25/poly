package kopo.poly.controller;

import kopo.poly.dto.BicycleDTO;
import kopo.poly.service.IApiService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class ApiTestController {

    @Resource(name = "ApiService")
    private IApiService apiService;


    @GetMapping(value = "/kakaoMap")
    public String kakaoMap() throws Exception {
        log.info(this.getClass().getName() + ".kakaoMap Start !!");
        log.info(this.getClass().getName() + ".kakaoMap End !!");
        return "/kakaoMap";
    }

    @GetMapping(value = "/apitest")
    public @ResponseBody BicycleDTO apiTest(HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + ".apiTest Start !!");
        String lat = CmmUtil.nvl(request.getParameter("lat"));
        String lon = CmmUtil.nvl(request.getParameter("lon"));
        log.info("lat : "+lat + " / lon : "+lon);
//        String lat = CmmUtil.nvl("37.544147");
//        String lon = CmmUtil.nvl("126.8357822");

        BicycleDTO bDTO = new BicycleDTO();

        bDTO.setLat(lat);
        bDTO.setLon(lon);

        BicycleDTO biDTO = apiService.apiTest(bDTO);
        log.info(this.getClass().getName() + ".apiTest End !!");
        return biDTO;
    }
}
