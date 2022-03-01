package com.sikhuchung.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sikhuchung.domain.CartVO;
import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.OrderDTO;
import com.sikhuchung.domain.PaymentDTO;
import com.sikhuchung.domain.UserVO;
import com.sikhuchung.service.SikhuchungService;

@Controller
public class SikhuchungController {

    @Autowired
    private SikhuchungService sikhuchungService;

    @GetMapping(value = "/sikhuchung/sikhuchungTest.do")
    public String sikhuchungTest() {
        return "sikhuchung/sikhuchungTest";
    }

    // 공지사항 글쓰기 이동
    @GetMapping(value = "/sikhuchung/noticewrite.do")
    public String openNoticeWrite(@RequestParam(value = "noticeNumber", required = false) Long noticeNumber,
            Model model) {
        if (noticeNumber == null) {
            model.addAttribute("notice", new NoticeDTO());
        } else {
            NoticeDTO notice = sikhuchungService.getNoticeDetail(noticeNumber);
            if (notice == null) {
                return "redirect:/skihuchung/noticelist.do";
            }
            model.addAttribute("notice", notice);
        }
        return "sikhuchung/noticewrite";
    }

    // 공지사항 글쓰기 처리
    @PostMapping(value = "/sikhuchung/register.do")
    public String registerNotice(NoticeDTO params) {
        try {
            boolean isRegistered = sikhuchungService.registerNotice(params);
            if (isRegistered == false) {
                // TODO => 게시글 등록에 실패하였다는 메시지를 전달
            }
        } catch (DataAccessException e) {
            // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

        } catch (Exception e) {
            // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
        }
        return "redirect:/sikhuchung/noticelist.do";
    }

    // 공지사항 리스트
    @GetMapping(value = "/sikhuchung/noticelist.do")
    public String openNoticeList(@ModelAttribute("params") NoticeDTO params, Model model) {
        List<NoticeDTO> noticeList = sikhuchungService.getNoticeList(params);
        model.addAttribute("noticeList", noticeList);
        return "sikhuchung/noticelist";
    }

    // 공지사항 상세리스트
    @GetMapping(value = "/sikhuchung/noticeview.do")
    public String openNoticeDetail(@RequestParam(value = "noticeNumber", required = false) Long noticeNumber,
            Model model) {
        if (noticeNumber == null) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/sikhuchung/noticelist.do";
        }
        NoticeDTO notice = sikhuchungService.getNoticeDetail(noticeNumber);
        if (notice == null || "Y".equals(notice.getNoticeDelete())) {
            // TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/sikhuchung/noticelist.do";
        }
        model.addAttribute("notice", notice);

        sikhuchungService.hitPlus(noticeNumber);
        return "sikhuchung/noticeview";
    }

    // 공지사항 삭제
    @PostMapping(value = "/sikhuchung/delete.do")
    public String deleteNotice(@RequestParam(value = "noticeNumber", required = false) Long noticeNumber) {
        if (noticeNumber == null) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/sikhuchung/noticelist.do";
        }
        try {
            boolean isDeleted = sikhuchungService.deleteNotice(noticeNumber);
            if (isDeleted == false) {
                // TODO => 게시글 삭제에 실패하였다는 메시지를 전달
            }
        } catch (DataAccessException e) {
            // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

        } catch (Exception e) {
            // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
        }

        return "redirect:/sikhuchung/noticelist.do";
    }

    // 후기 리스트
    @GetMapping(value = "/sikhuchung/reviewlist.do")
    public String openReviewList() {
        return "sikhuchung/reviewlist";
    }

    // 후기 등록
    @GetMapping(value = "/sikhuchung/reviewwrite.do")
    public String openReviewWrite() {
        return "sikhuchung/reviewwrite";
    }

    // 후기 수정
    @GetMapping(value = "/sikhuchung/reviewupdate.do")
    public String openReviewUpdate() {
        return "sikhuchung/reviewupdate";
    }

    // 로그인 화면이동
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
    @GetMapping(value = "/sikhuchung/join.do")
    public String joinForm() {
        return "sikhuchung/join";
    }

    // 회원가입진행 -- 현균
    @PostMapping(value = "/sikhuchung/join.do")
    public String userJoin(UserVO userVO) {
        sikhuchungService.joinUser(userVO);
        return "sikhuchung/login";
    }

    // 아이디 중복 검사 --현균
    @PostMapping(value = "/sikhuchung/userIdChk")
    @ResponseBody
    public String userIdChkPOST(String userId) throws Exception {
        /* System.out.print("memberIdChk() 진입"); */
        int result = sikhuchungService.idCheck(userId);
        if (result != 0) {
            return "fail"; // 중복 아이디가 존재
        } else {
            return "success"; // 중복 아이디 x
        }
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

    // 결제창 화면 -- 필립
    @GetMapping(value = "/sikhuchung/payment.do")
    public String payment() {
        return "sikhuchung/payment";
    }

    // 상세주문목록 화면 -- 필립
    @GetMapping(value = "/sikhuchung/orderlist.do")
    public String orderlist() {
        return "sikhuchung/orderlist";
    }

    // 장바구니 화면 -- 필립
    @GetMapping(value = "/sikhuchung/cart.do")
    public String cart(Model model) throws Exception {
        String userid = "test";
        List<CartVO> cartlist = sikhuchungService.cartlist(userid);
        model.addAttribute("cartlist", cartlist);
        return "sikhuchung/cart";
    }

    // 장바구니 -> 결제창
    @PostMapping(value = "sikhuchung/payment.do")
    public String paymentlist(HttpServletRequest request, Model model) throws Exception {

        String[] paymentlist = request.getParameterValues("select_product");
        // System.out.println(paymentlist[0]);
        List<CartVO> orderlist = new ArrayList<CartVO>();
        for (int i = 0; i < paymentlist.length; i++) {
            CartVO order = sikhuchungService.paymentlist(paymentlist[i]);
            orderlist.add(order);
        }
        model.addAttribute("orderlist", orderlist);
        return "sikhuchung/payment";
    }

    // 결제창 -> 메인 (메인하면 에러나서 우선 카트로 보냄)
    @PostMapping(value = "/sikhuchung/cart.do")
    public String orderlist(HttpServletRequest request, OrderDTO orderDto, PaymentDTO paymentDto) throws Exception {
//        System.out.println("테스트"); // 여기까지는 넘어옴

        sikhuchungService.order(orderDto); // 주문테이블 생성

//        int o_num = sikhuchungService.ordernumber(orderDto); // o_num -> 주문번호
//
//        sikhuchungService.pay(o_num, paymentDto); // 결제테이블
//
//        String[] orders = request.getParameterValues("cartNumber");
//        for (int i = 0; i < orders.length; i++) {
//            String product_num = sikhuchungService.product_num(orders[i]); // product_num -> 상품번호
//            String pay_count = sikhuchungService.pay_count(orders[i]); // pay_count -> 구매갯수
//            sikhuchungService.orderlist(product_num, pay_count, o_num); // 주문상
//        }
        return "redirect:/sikhuchung/cart.do";
    }
//    // 결제창 -> 메인 (메인하면 에러나서 우선 카트로 보냄)
//    @PostMapping(value = "/sikhuchung/cart.do")
//    public String orderlist(HttpServletRequest request, OrderDTO orderDto, 결제테이블DTO) throws Exception {
////        System.out.println("테스트"); // 여기까지는 넘어옴
//        
//        sikhuchungService.order(orderDto); // 주문테이블 생성
//        
//        int o_num = sikhuchungService.ordernumber(orderDto); // o_num -> 주문번호
//        
//        sikhuchungService.pay(o_num, 결제테이블DTO); // 결제테이블
//        
//        String[] orders = request.getParameterValues("cartNumber");
////        for (int i = 0; i < orders.length; i++) {
////            sikhuchungService.orderlist(orders[i], o_num); // 주문상세테이블
////        }
//        for (int i = 0; i < orders.length; i++) {
//            String 상품번호 = sikhuchungService.상품번호(orders[i]);
//            String 수량 = sikhuchungService.수량(orders[i]);
//            sikhuchungService.orderlist(상품번호, 수량, o_num); // 주문상
//        }
//        return "redirect:/sikhuchung/cart.do";
//    }

    // 장바구니(삭제)
    @ResponseBody // 주소로 반환되지 않고 적은값 그대로 반환
    @PostMapping(value = "/sikhuchung/cartdelete.do")
    public int cartDelete(HttpServletRequest request, @RequestParam(value = "checkBoxArr[]") List<String> checkBoxArr)
            throws Exception {
        int result = 0;
        int checkNum;

        for (String str : checkBoxArr) {
            checkNum = Integer.parseInt(str);
            // System.out.println(checkNum);
            sikhuchungService.deletecart(checkNum);
        }
        return result;
    }

}
