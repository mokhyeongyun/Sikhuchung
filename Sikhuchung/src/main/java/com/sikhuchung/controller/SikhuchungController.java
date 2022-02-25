package com.sikhuchung.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sikhuchung.constant.Method;
import com.sikhuchung.domain.NoticeDTO;
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
            @RequestParam(value = "noticeNumber", required = false) Long noticeNumber, Model model) {
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
    public String openReviewWrite() {
        return "sikhuchung/reviewwrite";
    }

    // 후기 수정 -- 유진
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

}
