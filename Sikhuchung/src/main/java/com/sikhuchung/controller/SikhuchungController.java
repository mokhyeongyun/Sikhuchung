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

    // 공지사항 리스트
    @GetMapping(value = "/sikhuchung/noticelist.do")
    public String openNoticeList(Model model) {
        List<NoticeDTO> noticeList = sikhuchungService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "sikhuchung/noticelist";
    }

    // 로그인 화면이동 -- 현균
    @GetMapping(value = "/sikhuchung/login")
    public String login() {
        return "sikhuchung/login";
    }

    // 로그인 진행 -- 현균
    @GetMapping(value = "/sikhuchung/login.do")
    public String userLogin() {
        return "sikhuchung/login";
    }

    // 회원가입 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/join")
    public String join() {
        return "sikhuchung/join";
    }

    // 회원가입진행 -- 현균
    @GetMapping(value = "/sikhuchung/join.do")
    public String userJoin() {
        return "sikhuchung/join";
    }

    // 아이디찾기 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/find_id.do")
    public String findId() {
        return "sikhuchung/find_id";
    }

    // 비밀번호찾기 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/find_pw.do")
    public String findPw() {
        return "sikhuchung/find_pw";
    }

    // 마이페이지-주문목록 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/mypageOrderInfo.do")
    public String mypageOrderInfo() {
        return "sikhuchung/mypageOrderInfo";
    }

    // 마이페이지-회원정보변경 비밀번호확인 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/mypageMemberInfoPwCheck.do")
    public String mypageMemberInfoPwCheck() {
        return "sikhuchung/mypageMemberInfoPwCheck";
    }

    // 마이페이지-회원정보변경 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/mypageMemberInfo.do")
    public String mypageMemberInfo() {
        return "sikhuchung/mypageMemberInfo";
    }

    // 마이페이지-회원탈퇴 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/mypageMemberQuit.do")
    public String mypageMemberQuit() {
        return "sikhuchung/mypageMemberQuit";
    }

}
