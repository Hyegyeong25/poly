package kopo.poly.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.service.INoticeService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller

public class NoticeController {
    @GetMapping(value = "index")
    public String indexPage() throws Exception {
        log.info(this.getClass().getName() + ".indexPage Start !!");
        log.info(this.getClass().getName() + ".indexPage End !!");
        return "index";
    }
    @GetMapping(value = "noticeInfo")
    public String noticeInfo() throws Exception {
        log.info(this.getClass().getName() + ".noticeInfo Start !!");
        log.info(this.getClass().getName() + ".noticeInfo End !!");
        return "form";
    }
    @PostMapping(value = "getNoticeData")
    public String getNoticeData(HttpServletRequest request, Model model) throws Exception{
        log.info(this.getClass().getName()+".getNoticeData Start!!");
        String title = CmmUtil.nvl(request.getParameter("title"));
        String name = CmmUtil.nvl(request.getParameter("reg_id"));
        String contents = CmmUtil.nvl(request.getParameter("contents"));

        log.info("title : "+title);
        log.info("name : "+name);
        log.info("contents : "+contents);

        model.addAttribute("title", title);
        model.addAttribute("name", name);
        model.addAttribute("contents", contents);

        log.info(this.getClass().getName()+".getNoticeData End!!");
        return "getNoticeData";
    }
    @Resource(name = "NoticeService")
    private INoticeService noticeService;

    @PostMapping(value = "getInsertNotice")
    public String getInsertNotice(HttpServletRequest request, Model model) throws Exception{
        log.info(this.getClass().getName()+".getInsertNotice Start!!");
        String reg_id = CmmUtil.nvl(request.getParameter("reg_id"));
        String title = CmmUtil.nvl(request.getParameter("title"));
        String contents = CmmUtil.nvl(request.getParameter("contents"));

        log.info("reg_id : "+reg_id);
        log.info("title : "+title);
        log.info("contents : "+contents);

        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setReg_id(reg_id);
        pDTO.setTitle(title);
        pDTO.setContents(contents);

        int res = noticeService.InsertNoticeInfo(pDTO);
        //저장되면 1의 값을 안되면 0의 값을 가져옴

        String msg;
        String url = "/getNoticeList";

        if(res > 0){
            msg = "등록에 성공하셨습니다.";
        } else {
            msg = "등록에 실패하셨습니다.";
        }
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName()+".getInsertNotice End!!");
        return "Redirect";
    }

    @RequestMapping(value = "getNoticeList")
    public String getNoticeList(HttpServletRequest request, Model model) throws Exception{
        log.info(this.getClass().getName()+".getNoticeList Start!!");

        List<NoticeDTO> rList = noticeService.getNoticeList();
        log.info("사이즈 : "+String.valueOf(rList.size()));
        if(rList == null){
            rList = new ArrayList<>();
        }
        model.addAttribute("rList", rList);

        log.info(this.getClass().getName()+".getNoticeList End!!");

        return "NoticeList";
    }

    @RequestMapping(value = "NoticeDetail")
    public String NoticeDetail(HttpServletRequest request, Model model) throws Exception{
        log.info(this.getClass().getName()+".NoticeDetail Start!!");
        String notice_seq = CmmUtil.nvl(request.getParameter("no"));

        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setNotice_seq(notice_seq);

        NoticeDTO rDTO = noticeService.getNoticeDetail(pDTO);
        if(rDTO == null){
            model.addAttribute("msg", "존재하지 않는 게시물입니다.");
            model.addAttribute("url", "getNoticeList");
            return "Redirect";
        }
        model.addAttribute("rDTO", rDTO);
        log.info(this.getClass().getName()+".NoticeDetail End!!");

        return "NoticeDetail";
    }
    @RequestMapping(value = "noticeDelete")
    public String noticeDelete(HttpServletRequest request, Model model) throws Exception{
        log.info(this.getClass().getName()+".noticeDelete Start!!");
        /*url에서 'no'로 보내기 때문에 컨트롤러에서 'no'로 받는다.*/
        String notice_seq = CmmUtil.nvl(request.getParameter("no"));
        log.info("notice_seq : "+notice_seq);

        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setNotice_seq(notice_seq);

        int res = noticeService.getNoticeDelete(pDTO);
        //저장되면 1의 값을 안되면 0의 값을 가져옴

        String msg;
        String url;

        if(res > 0){
            msg = "삭제에 성공하셨습니다.";
            url = "getNoticeList";
        } else {
            msg = "삭제에 실패하셨습니다.";
            url = "NoticeDetail?no="+notice_seq;
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.info(this.getClass().getName()+".noticeDelete End!!");
        return "Redirect";
    }
}
