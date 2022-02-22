package com.sikhuchung.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.UserVO;
import com.sikhuchung.mapper.SikhuchungMapper;

@Service
public class SikhuchungServiceImpl implements SikhuchungService {

    @Autowired
    private SikhuchungMapper sikhuchungMapper;

    @Override
    public boolean registerService(NoticeDTO params) {
        int queryResult = 0;

        if (params.getNoticeNumber() == 0) {
            queryResult = sikhuchungMapper.insertNotice(params);
        } else {
            queryResult = sikhuchungMapper.updateNotice(params);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public NoticeDTO getNoticeDetail(int noticeNumber) {
        return sikhuchungMapper.selectNoticeDetail(noticeNumber);
    }

    @Override
    public boolean deleteNotice(int noticeNumber) {
        int queryResult = 0;

        NoticeDTO notice = sikhuchungMapper.selectNoticeDetail(noticeNumber);

        if (notice != null && "N".equals(notice.getNoticeDelete())) {
            queryResult = sikhuchungMapper.deleteNotice(noticeNumber);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<NoticeDTO> getNoticeList() {
        List<NoticeDTO> noticeList = Collections.emptyList();

        int noticeTotalCount = sikhuchungMapper.selectNoticeTotalCount();

        if (noticeTotalCount > 0) {
            noticeList = sikhuchungMapper.selectNoticeList();
        }

        return noticeList;
    }

    /* 회원가입 */
    @Transactional
    @Override
    public void joinUser(UserVO userVO) {
        userVO.setUgrade("USER");
        sikhuchungMapper.saveUser(userVO);
    }

    /* 아이디 중복확인 */
    @Override
    public int idCheck(String userId) throws Exception {
        return sikhuchungMapper.idCheck(userId);
    }

}
