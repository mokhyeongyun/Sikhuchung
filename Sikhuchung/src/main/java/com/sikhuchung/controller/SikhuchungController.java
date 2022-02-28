package com.sikhuchung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.ProductVO;
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

    // 메인화면 -- 재훈
    @GetMapping(value = "/sikhuchung/main.do")
    public String main(ProductVO productVO, Model model) {
    	List<ProductVO> productList = sikhuchungService.getProductList();
    	model.addAttribute("product", productList);
    	List<ProductVO> seasonList = sikhuchungService.getSeasonList();
    	model.addAttribute("season", seasonList);
    	List<ProductVO> weirdList = sikhuchungService.getWeirdList();
    	model.addAttribute("weird", weirdList);
    	List<ProductVO> simpleList = sikhuchungService.getSimpleList();
    	model.addAttribute("simple", simpleList);
    	List<ProductVO> presentList = sikhuchungService.getPresentList();
    	model.addAttribute("present", presentList);
        return "/sikhuchung/main";
    }
    
    // 제철 과일 리스트 -- 재훈
    @GetMapping(value = "/sikhuchung/season.do")
    public String season(ProductVO productVO, Model model) {
    	List<ProductVO> seasonList = sikhuchungService.getSeasonList();
    	model.addAttribute("season", seasonList);
        return "/sikhuchung/season";
    }
 // 별난 과일 리스트 -- 재훈
    @GetMapping(value = "/sikhuchung/weird.do")
    public String weird(ProductVO productVO, Model model) {
    	List<ProductVO> weirdList = sikhuchungService.getWeirdList();
    	model.addAttribute("weird", weirdList);
        return "/sikhuchung/weird";
    }
 // 간편 과일 리스트 -- 재훈
    @GetMapping(value = "/sikhuchung/simple.do")
    public String simple(ProductVO productVO, Model model) {
    	List<ProductVO> simpleList = sikhuchungService.getSimpleList();
    	model.addAttribute("simple", simpleList);
        return "/sikhuchung/simple";
    }
 // 선물 과일 리스트 -- 재훈
    @GetMapping(value = "/sikhuchung/present.do")
    public String present(ProductVO productVO, Model model) {
    	List<ProductVO> presentList = sikhuchungService.getPresentList();
    	model.addAttribute("present", presentList);
        return "/sikhuchung/present";
    }
    
    // 상세 정보 -- 재훈
    @GetMapping(value = "/sikhuchung/detail.do")
    public String detail(int productNumber, Model model) {
    	ProductVO productvo = sikhuchungService.getProductData(productNumber);
    	if(productvo.getProductDelete().equals("N")) {    		
    		// 썸네일, 이름, 가격, 분류, 원산지, 배송방법, 재고, 상세설명img
    		model.addAttribute("thumbnail", productvo.getProductThumbnail());
    		model.addAttribute("name", productvo.getProductName());
    		model.addAttribute("price", productvo.getProductPrice());
    		model.addAttribute("category", productvo.getProductCategory());
    		model.addAttribute("origin", productvo.getProductOrigin());
    		model.addAttribute("delivery", productvo.getProductDelivery());
    		model.addAttribute("stock", productvo.getProductStock());
    		model.addAttribute("info", productvo.getProductInfo());
    		return "sikhuchung/detail";
    	} else {
    		return "redirect:/sikhuchung/main.do";
    	}
    	
    }
    
    // 상품 추가 -- 재훈
    @GetMapping(value = "/sikhuchung/item_regist.do")
    public String item_regist() {
        return "sikhuchung/item_regist";
    }
    // 상품 수정 -- 재훈
    @GetMapping(value = "/sikhuchung/item_modify.do")
    public String item_modify() {
        return "sikhuchung/item_modify";
    }   
}
