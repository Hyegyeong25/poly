package kopo.poly.controller;

import kopo.poly.dto.NoticeDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.INoticeService;
import kopo.poly.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller

public class UserController {
    @Resource(name = "UserService")
    private IUserService UserService;

    @GetMapping(value = "userForm")
    public String userForm() throws Exception {
        log.info(this.getClass().getName() + ".userForm Start !!");
        log.info(this.getClass().getName() + ".userForm End !!");
        return "userForm";
    }
    @GetMapping(value = "WelcomeUser")
    public String WelcomeUser() throws Exception {
        log.info(this.getClass().getName() + ".WelcomeUser Start !!");
        log.info(this.getClass().getName() + ".WelcomeUser End !!");
        return "WelcomeUser";
    }
    @GetMapping(value = "userInsert")
    public String userInsert(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + ".userInsert Start !!");
        String user_id = request.getParameter("user_id");
        String user_pwd = request.getParameter("user_pwd");
        String user_email = request.getParameter("user_email");
        String user_name = request.getParameter("user_name");

        log.info("받아온 아이디 : "+user_id);
        log.info("받아온 비번 : "+user_pwd);
        log.info("받아온 이메일 : "+user_email);
        log.info("받아온 이름 : "+user_name);

        UserInfoDTO uDTO = new UserInfoDTO();
        uDTO.setUser_id(user_id);
        uDTO.setUser_pwd(user_pwd);
        uDTO.setUser_email(user_email);
        uDTO.setUser_name(user_name);

        int res = UserService.InsertUserInfo(uDTO);
        String msg;
        String url;

        if(res > 0){
            msg = "등록에 성공하셨습니다.";
            url = "/WelcomeUser";
        } else {
            msg = "등록에 실패하셨습니다.";
            url = "/index";
        }
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName() + ".userInsert End !!");
        return "Redirect";
    }
}
