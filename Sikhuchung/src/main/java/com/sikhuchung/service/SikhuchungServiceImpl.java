package com.sikhuchung.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.UserVO;
import com.sikhuchung.domain.ProductVO;
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

    /* 회원가입 --현균 */
    @Transactional
    @Override
    public void joinUser(UserVO userVO) {
        userVO.setUser_grade("USER");
        sikhuchungMapper.saveUser(userVO);
    }

    /* 아이디 중복확인 --현균 */
    @Override
    public int idCheck(String userId) throws Exception {
        return sikhuchungMapper.idCheck(userId);
    }

    /* 로그인 */
    @Override
    public int userLogin(UserVO userVO) throws Exception {
        return sikhuchungMapper.userLogin(userVO);
    }

    /* 아이디 찾기 */
    @Override
    public String findIdCheck(UserVO userVO) throws Exception {
        return sikhuchungMapper.findIdCheck(userVO);
    }

    /* 비밀번호 찾기 */
    @Override
    public String findPwCheck(UserVO userVO) throws Exception {
        return sikhuchungMapper.findPwCheck(userVO);
    }

    @Override
    public boolean hitPlus(Long noticeNumber) {
        return sikhuchungMapper.hitPlus(noticeNumber);
    }
    
    /* 메인 화면 -- 재훈 */
    @Override
    public List<ProductVO> getProductList() {
    	List<ProductVO> productList = sikhuchungMapper.selectProductList();
    	return productList;
    }
    
    /* 메인화면 제철, 간편, 별난, 선물 -- 재훈 */
    @Override
    public List<ProductVO> getPresentList() {
    	List<ProductVO> productList = sikhuchungMapper.selectPresentList();
    	return productList;
    }
    @Override
    public List<ProductVO> getSimpleList() {
    	List<ProductVO> productList = sikhuchungMapper.selectSimpleList();
    	return productList;
    }
    @Override
    public List<ProductVO> getSeasonList() {
    	List<ProductVO> productList = sikhuchungMapper.selectSeasonList();
    	return productList;
    }
    @Override
    public List<ProductVO> getWeirdList() {
    	List<ProductVO> productList = sikhuchungMapper.selectWeirdList();
    	return productList;
    }
    
    /* 상세화면 -- 재훈 */
    @Override
    public ProductVO getProductData(int productNumber) {
    	ProductVO productData = sikhuchungMapper.selectProductData(productNumber);
    	return productData;
    }
}
