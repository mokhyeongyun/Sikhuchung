package com.sikhuchung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.service.SikhuchungService;

@Controller
public class SikhuchungController {

    @Autowired
    private SikhuchungService sikhuchungService;

    @GetMapping(value = "/sikhuchung/sikhuchungTest.do")
    public String sikhuchungTest() {

        return "sikhuchung/sikhuchungTest";
    }

    @GetMapping(value = "/sikhuchung/noticelist.do")
    public String openNoticeList(Model model) {
        List<NoticeDTO> noticeList = sikhuchungService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "sikhuchung/noticelist";
    }

}
