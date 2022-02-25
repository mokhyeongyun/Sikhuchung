package com.sikhuchung.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.ReviewDTO;
import com.sikhuchung.domain.UserVO;
import com.sikhuchung.mapper.SikhuchungMapper;
import com.sikhuchung.paging.PaginationInfo;

@Service
public class SikhuchungServiceImpl implements SikhuchungService {

    @Autowired
    private SikhuchungMapper sikhuchungMapper;

    /* 공지사항 */
    @Override
    public boolean registerNotice(NoticeDTO params) {
        int queryResult = 0;

        if (params.getNoticeNumber() == null) {
            queryResult = sikhuchungMapper.insertNotice(params);
        } else {
            queryResult = sikhuchungMapper.updateNotice(params);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public NoticeDTO getNoticeDetail(Long noticeNumber) {
        return sikhuchungMapper.selectNoticeDetail(noticeNumber);
    }

    @Override
    public boolean deleteNotice(Long noticeNumber) {
        int queryResult = 0;

        NoticeDTO notice = sikhuchungMapper.selectNoticeDetail(noticeNumber);

        if (notice != null && "N".equals(notice.getNoticeDelete())) {
            queryResult = sikhuchungMapper.deleteNotice(noticeNumber);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<NoticeDTO> getNoticeList(NoticeDTO params) {
        List<NoticeDTO> noticeList = Collections.emptyList();

        int noticeTotalCount = sikhuchungMapper.selectNoticeTotalCount(params);

        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(noticeTotalCount);

        params.setPaginationInfo(paginationInfo);

        if (noticeTotalCount > 0) {
            noticeList = sikhuchungMapper.selectNoticeList(params);
        }

        return noticeList;
    }

    @Override
    public boolean hitPlus(Long noticeNumber) {
        return sikhuchungMapper.hitPlus(noticeNumber);
    }

    /* 후기 */
    @Override
    public boolean registerReview(ReviewDTO params) {
        int queryResult = 0;

        if (params.getReviewNumber() == null) {
            queryResult = sikhuchungMapper.insertReview(params);
        } else {
            queryResult = sikhuchungMapper.updateReview(params);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public ReviewDTO getReviewDetail(Long reviewNumber) {
        return sikhuchungMapper.selectReviewDetail(reviewNumber);
    }

    @Override
    public boolean deleteReview(Long reviewNumber) {
        int queryResult = 0;

        ReviewDTO review = sikhuchungMapper.selectReviewDetail(reviewNumber);

        if (review != null && "N".equals(review.getReviewDelete())) {
            queryResult = sikhuchungMapper.deleteReview(reviewNumber);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<ReviewDTO> getReviewList() {
        List<ReviewDTO> reviewList = Collections.emptyList();

        int reviewTotalCount = sikhuchungMapper.selectReviewTotalCount();

        if (reviewTotalCount > 0) {
            reviewList = sikhuchungMapper.selectReviewList();
        }

        return reviewList;
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
