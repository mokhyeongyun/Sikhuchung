package com.sikhuchung.service;

import java.util.List;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.UserVO;
import com.sikhuchung.domain.ProductVO;

public interface SikhuchungService {
    /* 공지사항 */
    public boolean registerNotice(NoticeDTO params);

    public NoticeDTO getNoticeDetail(Long noticeNumber);

    public boolean deleteNotice(Long noticeNumber);

    public List<NoticeDTO> getNoticeList(NoticeDTO params);
    
    // 메인화면 -- 재훈
    public List<ProductVO> getProductList();
    
    // 메인화면 제철, 간편, 별난, 선물 -- 재훈
    public List<ProductVO> getPresentList();
    public List<ProductVO> getSimpleList();
    public List<ProductVO> getSeasonList();
    public List<ProductVO> getWeirdList(); 
    
    // 상세화면 -- 재훈
    public ProductVO getProductData(int productNumber);

    public boolean hitPlus(Long noticeNumber);

    /* 후기 */

    /* 회원가입 */
    public void joinUser(UserVO userVO);

    // 아이디 중복 검사
    public int idCheck(String userId) throws Exception;

    // 로그인
    public int userLogin(UserVO userVO) throws Exception;

    // 아이디찾기
    public String findIdCheck(UserVO userVO) throws Exception;

    // 비밀번호찾기
    public String findPwCheck(UserVO userVO) throws Exception;

}
