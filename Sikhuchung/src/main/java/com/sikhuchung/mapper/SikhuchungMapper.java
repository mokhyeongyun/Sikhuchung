package com.sikhuchung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.OrderDetailDTO;
import com.sikhuchung.domain.UserVO;
import com.sikhuchung.domain.ProductVO;

@Mapper
public interface SikhuchungMapper {
    /* 공지사항 */
    public int insertNotice(NoticeDTO params);

    public NoticeDTO selectNoticeDetail(Long noticeNumber);

    public int updateNotice(NoticeDTO params);

    public int deleteNotice(Long noticeNumber);

    public List<NoticeDTO> selectNoticeList(NoticeDTO params);

    public int selectNoticeTotalCount(NoticeDTO params);

    public boolean hitPlus(Long noticeNumberLong);

    /* 후기 */

    public int selectNoticeTotalCount();

    /* 회원가입 */
    public void saveUser(UserVO userVO);

    /* id중복확인 */
    public int idCheck(String userId);

    /* 로그인 */
    public int userLogin(UserVO userVO);

    /* 아이디 찾기 */
    public String findIdCheck(UserVO userVO);

    /* 비밀번호 찾기 */
    public String findPwCheck(UserVO userVO);
<<<<<<< HEAD
    
    /* 메인화면 -- 재훈 */
    public List<ProductVO> selectProductList();
    
    /* 메인화면 제철, 간편, 별난, 선물 -- 재훈 */
    public List<ProductVO> selectPresentList();
    public List<ProductVO> selectSimpleList();
    public List<ProductVO> selectSeasonList();
    public List<ProductVO> selectWeirdList();
    
    /* 상세화면 -- 재훈 */
    public ProductVO selectProductData(int productNumber);
=======

    // 마이페이지 회원정보수정 비밀번호체크
    public int memberInfoPwCheck(UserVO userVO);

    // 마이페이지 회원정보수정 보여주기
    public UserVO memberInfo(UserVO userVO);

    // 마이페이지 회원정보수정 진행
    public void memberInfoUpdate(UserVO userVO);

    // 마이페이지 회원탈퇴 진행
    public void memberQuit(UserVO userVO);

    // 마이페이지 주문목록 리스트
    public List<OrderDetailDTO> getOrderList(String user);
>>>>>>> mok
}
