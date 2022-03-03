package com.sikhuchung.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.sikhuchung.constant.Method;
import com.sikhuchung.domain.CartVO;
import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.OrderDTO;
import com.sikhuchung.domain.OrderDetailDTO;
import com.sikhuchung.domain.PaymentDTO;
import com.sikhuchung.domain.ProductVO;
import com.sikhuchung.domain.ReviewDTO;
import com.sikhuchung.domain.UserVO;
import com.sikhuchung.service.SikhuchungService;
import com.sikhuchung.util.UiUtils;

@Controller
public class SikhuchungController extends UiUtils {

    @Autowired
    private SikhuchungService sikhuchungService;

    // 공지사항 글쓰기 이동 -- 유진
    @GetMapping(value = "/sikhuchung/noticewrite.do")
    public String openNoticeWrite(@ModelAttribute("params") NoticeDTO params,
            @RequestParam(value = "noticeNumber", required = false) Long noticeNumber, Model model,
            HttpServletRequest req) {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if (session.getAttribute("user") == null) {
            return "sikhuchung/login";
        } else {

            if (noticeNumber == null) {
                model.addAttribute("notice", new NoticeDTO());
            } else {
                NoticeDTO notice = sikhuchungService.getNoticeDetail(noticeNumber);
                if (notice == null) {
                    return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/sikhuchung/noticelist.do", Method.GET,
                            null, model);
                }
                model.addAttribute("notice", notice);
            }

            return "sikhuchung/noticewrite";
        }
    }

    // 공지사항 글쓰기 처리 -- 유진
    @PostMapping(value = "/sikhuchung/register.do")
    public String registerNotice(@ModelAttribute("params") final NoticeDTO params, Model model) {
        Map<String, Object> pagingParams = getPagingParams(params);
        try {
            boolean isRegistered = sikhuchungService.registerNotice(params);
            if (isRegistered == false) {
                return showMessageWithRedirect("게시글 쓰기에 실패하였습니다.", "/sikhuchung/noticelist.do", Method.GET,
                        pagingParams, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/sikhuchung/noticelist.do", Method.GET,
                    pagingParams, model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/sikhuchung/noticelist.do", Method.GET, pagingParams,
                    model);
        }
        return showMessageWithRedirect("게시글 쓰기가 완료되었습니다.", "/sikhuchung/noticelist.do", Method.GET, pagingParams,
                model);
    }

    // 공지사항 리스트 -- 유진
    @GetMapping(value = "/sikhuchung/noticelist.do")
    public String openNoticeList(@ModelAttribute("params") NoticeDTO params, Model model) {
        List<NoticeDTO> noticeList = sikhuchungService.getNoticeList(params);
        model.addAttribute("noticeList", noticeList);
        return "sikhuchung/noticelist";
    }

    // 공지사항 상세리스트 -- 유진
    @GetMapping(value = "/sikhuchung/noticeview.do")
    public String openNoticeDetail(@ModelAttribute("params") NoticeDTO params,
            @RequestParam(value = "noticeNumber", required = false) Long noticeNumber, Model model) {
        if (noticeNumber == null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/sikhuchung/noticelist.do", Method.GET, null, model);

        }
        NoticeDTO notice = sikhuchungService.getNoticeDetail(noticeNumber);
        if (notice == null || "Y".equals(notice.getNoticeDelete())) {
            return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/sikhuchung/noticelist.do", Method.GET, null,
                    model);
        }
        model.addAttribute("notice", notice);

        sikhuchungService.hitPlus(noticeNumber);
        return "sikhuchung/noticeview";
    }

    // 공지사항 삭제 -- 유진
    @PostMapping(value = "/sikhuchung/delete.do")
    public String deleteNotice(@ModelAttribute("params") NoticeDTO params,
            @RequestParam(value = "noticeNumber", required = false) Long noticeNumber, Model model) {
        if (noticeNumber == null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/skihuchung/noticelist.do", Method.GET, null, model);
        }
        Map<String, Object> pagingParams = getPagingParams(params);
        try {
            boolean isDeleted = sikhuchungService.deleteNotice(noticeNumber);
            if (isDeleted == false) {
                return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/sikhuchung/noticelist.do", Method.GET,
                        pagingParams, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/skihuchung/noticelist.do", Method.GET,
                    pagingParams, model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/sikhuchung/noticelist.do", Method.GET, pagingParams,
                    model);
        }

        return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/sikhuchung/noticelist.do", Method.GET, pagingParams,
                model);
    }

    // 후기 리스트 -- 유진
    @GetMapping(value = "/sikhuchung/reviewlist.do")
    public String openReviewList(Model model) {
        List<ReviewDTO> reviewList = sikhuchungService.getReviewList();
        model.addAttribute("reviewList", reviewList);

        return "sikhuchung/reviewlist";
    }

    // 후기 등록 -- 유진
    @GetMapping(value = "/sikhuchung/reviewwrite.do")
    public String openReviewWrite(int orderDetailNumber, Model model) {

        System.out.println(orderDetailNumber);
        String productName = sikhuchungService.getProductName(orderDetailNumber);
        System.out.println(productName);
        model.addAttribute("productName", productName);
        model.addAttribute("orderDetailNumber", orderDetailNumber);

        return "sikhuchung/reviewwrite";
    }

    // 후기 등록 처리 -- 유진
    @PostMapping(value = "/sikhuchung/registerreview.do")
    public String registerReview(ReviewDTO reviewdto) {

        System.out.println(reviewdto.getReviewRate());
        Long result = reviewdto.getOrderDetailNumber();
        sikhuchungService.registerReview(reviewdto);
        sikhuchungService.updateresult(result);
        return "redirect:/sikhuchung/reviewlist.do";
    }

    // 후기 수정 화면 -- 유진
    @GetMapping(value = "/sikhuchung/reviewupdate.do")
    public String openReview(int orderDetailNumber, Model model) {

        ReviewDTO reviewdto = sikhuchungService.getreviewdto(orderDetailNumber);
        String productName = sikhuchungService.getProductName(orderDetailNumber);
        model.addAttribute("reviewDTO", reviewdto);
        model.addAttribute("productName", productName);
        model.addAttribute("orderDetailNumber", orderDetailNumber);

        return "sikhuchung/reviewupdate";
    }

    // 후기 수정 -- 유진
    @PostMapping(value = "/sikhuchung/reviewupdate.do")
    public String openReviewUpdate(ReviewDTO reviewdto) {

        sikhuchungService.reviewUpdate(reviewdto);

        return "redirect:/sikhuchung/mypageOrderInfo.do";
    }

    // 후기 삭제-- 유진
    @GetMapping(value = "/sikhuchung/reviewdelete.do")
    public String reviewDelete(int orderDetailNumber, Model model) {

        sikhuchungService.reviewDelete(orderDetailNumber);
        sikhuchungService.resultChange(orderDetailNumber);
        return "redirect:/sikhuchung/mypageOrderInfo.do";
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

    // 결제창 화면 -- 필립
    @GetMapping(value = "/sikhuchung/payment.do")
    public String payment() {
        return "sikhuchung/payment";
    }

    // 상세주문목록 화면 -- 필립
    @GetMapping(value = "/sikhuchung/paymentlist.do")
    public String plist(Model model) throws Exception {

        List<OrderDTO> plist = sikhuchungService.plist();
//        System.out.println(plist);
        model.addAttribute("paylist", plist);
        return "sikhuchung/paymentlist";
    }

    // 장바구니 화면 -- 필립
    @GetMapping(value = "/sikhuchung/cart.do")
    public String cart(Model model) throws Exception {
        String userid = "test";
        List<CartVO> cartlist = sikhuchungService.cartlist(userid);
        model.addAttribute("cartlist", cartlist);
        return "sikhuchung/cart";
    }

    // 장바구니 -> 결제창 -- 필립
    @PostMapping(value = "sikhuchung/payment.do")
    public String paymentlist(HttpServletRequest request, Model model) throws Exception {

        String[] paymentlist = request.getParameterValues("select_product");
        // System.out.println(paymentlist[0]);
        List<CartVO> orderlist = new ArrayList<CartVO>();
        int amount = 0;
        int count = 0;
        int dc = 0;
        int allamount = 0;
        for (int i = 0; i < paymentlist.length; i++) {
            CartVO order = sikhuchungService.paymentlist(paymentlist[i]);
            amount += order.getPriceAmount();
            count += order.getCartCount();
            orderlist.add(order);
        }
        if (count >= 5 && count < 10) {
            dc = (int) (amount * 0.1);
            allamount = amount - dc;
        } else if (count >= 10) {
            dc = (int) (amount * 0.2);
            allamount = amount - dc;
        }
        model.addAttribute("dc", dc);
        model.addAttribute("priceamount", amount);
        model.addAttribute("allamount", allamount);
        model.addAttribute("orderlist", orderlist);
        return "sikhuchung/payment";
    }

    // 결제창 -> 메인 (메인하면 에러나서 우선 카트로 보냄) -- 필립
    @PostMapping(value = "/sikhuchung/cart.do")
    public String orderlist(HttpServletRequest request, OrderDTO orderDto, PaymentDTO paymentDto) throws Exception {
        sikhuchungService.order(orderDto); // 주문테이블 삽입 성공
        int ordernumber = sikhuchungService.ordernumber22();

        String[] orderDetailCountlist = request.getParameterValues("orderDetailCount");
        String[] productNumberlist = request.getParameterValues("productNumber");

        for (int i = 0; i < orderDetailCountlist.length; i++) {
            OrderDetailDTO orderdetail = new OrderDetailDTO();
            int orderdetailcount = Integer.parseInt(orderDetailCountlist[i]);
            int productnumber = Integer.parseInt(productNumberlist[i]);

            orderdetail.setOrderDetailCount(orderdetailcount);
            orderdetail.setProductNumber(productnumber);
            orderdetail.setOrderNumber(ordernumber);
            sikhuchungService.orderDetailDTO(orderdetail);
        }

        paymentDto.setOrderNumber(ordernumber);
        sikhuchungService.paymentDTO(paymentDto); // 결제 테이블

        String[] cartNumberlist = request.getParameterValues("cartNumber");
        for (int i = 0; i < cartNumberlist.length; i++) {
            int cartNumber = Integer.parseInt(cartNumberlist[i]);
            sikhuchungService.cartOrderDelete(cartNumber);
        }

        return "redirect:/sikhuchung/cart.do";
    }

    // 장바구니 선택 삭제 -- 필립
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

    // 주문목록 선택 삭제(관리자버전) -- 필립
    @ResponseBody // 주소로 반환되지 않고 적은값 그대로 반환
    @PostMapping(value = "/sikhuchung/deleteOrderlist.do")
    public int deleteOrdelist(HttpServletRequest request,
            @RequestParam(value = "checkBoxArr[]") List<String> checkBoxArr) throws Exception {
        int result = 0;
        int checkNum;

        for (String str : checkBoxArr) {
            checkNum = Integer.parseInt(str);
            System.out.println(checkNum);
            sikhuchungService.deleteOrderlist(checkNum);
        }
        return result;
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
            model.addAttribute("productNumber", productvo.getProductNumber());
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

    // 장바구니 등록 -- 재훈
    @PostMapping(value = "/sikhuchung/test.do")
    public String detail(CartVO cartvo) {
        System.out.println(cartvo.getProductNumber());
        sikhuchungService.getItem(cartvo);
        return "redirect:/sikhuchung/cart.do";
    }
}
