package com.sikhuchung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sikhuchung.service.SikhuchungService;

@Controller
public class SikhuchungController {

    @Autowired
    private SikhuchungService sikhuchungService;

    @GetMapping(value = "/sikhuchung/sikhuchungTest.do")
    public String sikhuchungTest() {

        return "sikhuchung/sikhuchungTest";
    }

    // 로그인 화면이동
    @GetMapping(value = "/sikhuchung/login.do")
    public String login() {
        return "sikhuchung/login";
    }

    // 회원가입 화면 이동
    @GetMapping(value = "/sikhuchung/join.do")
    public String join() {
        return "sikhuchung/join";
    }

    // 아이디찾기 화면 이동
    @GetMapping(value = "/sikhuchung/find_id.do")
    public String findId() {
        return "sikhuchung/find_id";
    }

    // 비밀번호찾기 화면 이동
    @GetMapping(value = "/sikhuchung/find_pw.do")
    public String findPw() {
        return "sikhuchung/find_pw";
    }

    // 장바구나 화면
    @GetMapping(value = "/sikhuchung/cart.do")
    public String cart() {
        return "sikhuchung/cart";
    }

    // 결제창 화면
    @GetMapping(value = "/sikhuchung/payment.do")
    public String payment() {
        return "sikhuchung/payment";
    }

    // 주문목록 화면
    @GetMapping(value = "/sikhuchung/orderlist.do")
    public String orderlist() {
        return "sikhuchung/orderlist";
    }

}
