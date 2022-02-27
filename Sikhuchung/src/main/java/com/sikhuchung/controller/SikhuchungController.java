package com.sikhuchung.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.OrderDetailDTO;
import com.sikhuchung.domain.ProductVO;
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
    @GetMapping(value = "/sikhuchung/login.do")
    public String login() {
        return "sikhuchung/login";
    }

    // 로그인 진행 -- 현균
    @PostMapping(value = "/sikhuchung/login.do")
    @ResponseBody
    public String userLogin(UserVO userVO, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
        System.out.println(userVO.getUserId());
        System.out.println(userVO.getUserPw());
        HttpSession session = req.getSession();
        int result = sikhuchungService.userLogin(userVO);

        if (result == 0) {
            session.setAttribute("user", null);
            rttr.addFlashAttribute("msg", false);
            return "fail";
        } else {
            session.setAttribute("user", userVO.getUserId());
            return "true";
        }

    }

    // 로그아웃 --현균
    @GetMapping("/sikhuchung/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "sikhuchung/main"; // 주소 요청으로 변경
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

    // 아이디찾기 진행 -- 현균
    @PostMapping(value = "/sikhuchung/find_id.do")
    public String findIdPOST(UserVO userVO, Model model) throws Exception {
        if (sikhuchungService.findIdCheck(userVO) == null) {
            model.addAttribute("msg", "일치하는 회원정보가 없습니다.");
            return "/sikhuchung/find_id";
        } else {
            model.addAttribute("user", sikhuchungService.findIdCheck(userVO));
            return "sikhuchung/find_id";
        }
    }

    // 비밀번호찾기 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/find_pw.do")
    public String findPw() {
        return "sikhuchung/find_pw";
    }

    // 비밀번호찾기 진행 -- 현균
    @PostMapping(value = "/sikhuchung/find_pw.do")
    public String findPwPOST(UserVO userVO, Model model) throws Exception {
        if (sikhuchungService.findPwCheck(userVO) == null) {
            model.addAttribute("msg", "일치하는 회원정보가 없습니다.");
            return "/sikhuchung/find_pw";
        } else {
            model.addAttribute("user", sikhuchungService.findPwCheck(userVO));
            return "sikhuchung/find_pw";
        }
    }

    // 마이페이지-주문목록 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/mypageOrderInfo.do")
    public String mypageOrderInfo(Model model, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if (session.getAttribute("user") == null) {
            // System.out.println("세션없음");
            return "sikhuchung/login";
        } else {
            List<OrderDetailDTO> orderList = sikhuchungService.getOrderList(user);
            model.addAttribute("orderList", orderList);
            model.addAttribute("length", orderList.size());
            return "sikhuchung/mypageOrderInfo";
        }
    }

    // 마이페이지-회원정보변경 비밀번호확인 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/mypageMemberInfoPwCheck.do")
    public String mypageMemberInfoPwCheck(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            // System.out.println("세션없음");
            return "sikhuchung/login";
        } else {
            // System.out.println("세션있음");
            return "sikhuchung/mypageMemberInfoPwCheck";
        }
    }

    // 마이페이지-회원정보변경 비밀번호확인 화면 진행 -- 현균
    @PostMapping(value = "/sikhuchung/mypageMemberInfoPwCheck.do")
    public String memberInfoPwCheck(UserVO userVO, Model model) throws Exception {
        int result = sikhuchungService.memberInfoPwCheck(userVO);
        UserVO userInfo = sikhuchungService.memberInfo(userVO);
        if (result == 0) {
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            return "/sikhuchung/mypageMemberInfoPwCheck";
        } else {
            model.addAttribute("check", "true");
            model.addAttribute("userId", userInfo.getUserId());
            model.addAttribute("userPw", userInfo.getUserPw());
            model.addAttribute("userName", userInfo.getUserName());
            model.addAttribute("userEmail", userInfo.getUserEmail());
            model.addAttribute("userTel", userInfo.getUserTel());
            return "/sikhuchung/mypageMemberInfo";
        }
    }

    // 마이페이지-회원정보변경 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/mypageMemberInfo.do")
    public String mypageMemberInfo(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            // System.out.println("세션없음");
            return "sikhuchung/login";
        } else {
            // System.out.println("세션있음");
            if (model.getAttribute("check") == null) { // 비밀번호 확인 미완료
                return "sikhuchung/mypageMemberInfoPwCheck";
            } else {
                return "sikhuchung/mypageMemberInfo";
            }
        }
    }

    // 마이페이지-회원정보변경 진행 -- 현균
    @PostMapping(value = "/sikhuchung/mypageMemberInfo.do")
    public String memberInfoUpdate(UserVO uservo, HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            // System.out.println("세션없음");
            return "sikhuchung/login";
        } else {
            // System.out.println("세션있음");
            sikhuchungService.memberInfoUpdate(uservo);
            return "sikhuchung/mypageOrderInfo";
        }
    }

    // 마이페이지-회원탈퇴 화면 이동 -- 현균
    @GetMapping(value = "/sikhuchung/mypageMemberQuit.do")
    public String mypageMemberQuit(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            // System.out.println("세션없음");
            return "sikhuchung/login";
        } else {
            // System.out.println("세션있음");
            return "sikhuchung/mypageMemberQuit";
        }
    }

    // 마이페이지-회원탈퇴 진행 -- 현균
    @PostMapping(value = "/sikhuchung/mypageMemberQuit.do")
    @ResponseBody
    public String MemberQuit(UserVO userVO, HttpServletRequest req, HttpSession session, Model model) throws Exception {
        int result = sikhuchungService.memberInfoPwCheck(userVO);
        if (result == 0) {
            return "fail";
        } else {
            sikhuchungService.memberQuit(userVO);
            session.invalidate();
            return "true";
        }
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

    // 상세 정보 -- 재훈
    @GetMapping(value = "/sikhuchung/detail.do")
    public String detail(int productNumber, Model model) {
        ProductVO productvo = sikhuchungService.getProductData(productNumber);
        if (productvo.getProductDelete().equals("N")) {
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
