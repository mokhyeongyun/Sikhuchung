package com.sikhuchung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
