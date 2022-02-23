package com.sikhuchung.service;

import java.util.List;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.UserVO;

public interface SikhuchungService {
    public boolean registerService(NoticeDTO params);

    public NoticeDTO getNoticeDetail(int noticeNumber);

    public boolean deleteNotice(int noticeNumber);

    public List<NoticeDTO> getNoticeList();

    /* 회원가입 */
    public void joinUser(UserVO userVO);

    // 아이디 중복 검사
    public int idCheck(String userId) throws Exception;

}
