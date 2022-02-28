package com.sikhuchung.service;

import java.util.List;

import com.sikhuchung.domain.NoticeDTO;
<<<<<<< HEAD
import com.sikhuchung.domain.OrderDetailDTO;
=======
import com.sikhuchung.domain.ReviewDTO;
>>>>>>> yj
import com.sikhuchung.domain.UserVO;
import com.sikhuchung.domain.ProductVO;

public interface SikhuchungService {
	
    /* 공지사항 */
    public boolean registerNotice(NoticeDTO params);

    public NoticeDTO getNoticeDetail(Long noticeNumber);

    public boolean deleteNotice(Long noticeNumber);

    public List<NoticeDTO> getNoticeList(NoticeDTO params);
    
    /* 메인화면 -- 재훈 */
    public List<ProductVO> getProductList();
    
    /*  메인화면 제철, 간편, 별난, 선물 -- 재훈 */
    public List<ProductVO> getPresentList();
    public List<ProductVO> getSimpleList();
    public List<ProductVO> getSeasonList();
    public List<ProductVO> getWeirdList(); 
    
    /* 상세화면 -- 재훈 */
    public ProductVO getProductData(int productNumber);

    public boolean hitPlus(Long noticeNumber);

<<<<<<< HEAD
=======
    /* 후기 */
    public boolean registerReview(ReviewDTO params);

    public ReviewDTO getReviewDetail(Long reviewNumber);

    public boolean deleteReview(Long reviewNumber);

    public List<ReviewDTO> getReviewList();

>>>>>>> yj
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

    // 마이페이지 회원정보수정 비밀번호체크
    public int memberInfoPwCheck(UserVO userVO) throws Exception;

    // 마이페이지 회원정보수정 보여주기
    public UserVO memberInfo(UserVO userVO);

    // 회원정보변경
    public void memberInfoUpdate(UserVO userVO);

    // 회원탈퇴
    public void memberQuit(UserVO userVO) throws Exception;

    // 마이페이지 주문목록
    public List<OrderDetailDTO> getOrderList(String user) throws Exception;

}
