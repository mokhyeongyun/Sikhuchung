package com.sikhuchung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sikhuchung.domain.CartVO;
import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.OrderDTO;
import com.sikhuchung.domain.OrderDetailDTO;
import com.sikhuchung.domain.PaymentDTO;
import com.sikhuchung.domain.ProductVO;
import com.sikhuchung.domain.ReviewDTO;
import com.sikhuchung.domain.UserVO;

@Mapper
public interface SikhuchungMapper {
    /* 공지사항 */
    public int insertNotice(NoticeDTO params);

    public NoticeDTO selectNoticeDetail(Long noticeNumber);

    public int updateNotice(NoticeDTO params);

    public int deleteNotice(Long noticeNumber);

    public List<NoticeDTO> selectNoticeList(NoticeDTO params);

    public int selectNoticeTotalCount(NoticeDTO params);

    public boolean hitPlus(Long noticeNumber);

    /* 후기 */
    public int insertReview(ReviewDTO params);

    public ReviewDTO selectReviewDetail(Long reviewNumber);

    public int updateReview(ReviewDTO params);

    public int deleteReview(Long reviewNumber);

    public List<ReviewDTO> selectReviewList();

    public int selectReviewTotalCount();

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

    /* 메인화면 -- 재훈 */
    public List<ProductVO> selectProductList();

    /* 메인화면 제철, 간편, 별난, 선물 -- 재훈 */
    public List<ProductVO> selectPresentList();

    public List<ProductVO> selectSimpleList();

    public List<ProductVO> selectSeasonList();

    public List<ProductVO> selectWeirdList();

    /* 상세화면 -- 재훈 */
    public ProductVO selectProductData(int productNumber);
    
    /* 장바구니 넣기 -- 재훈 */
    public void item(CartVO cartvo);

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

    /* 장바구니 목록 */
    public List<CartVO> cartlist(String userid);

    /* 결제창 */
    public CartVO paymentlist(String paymentlist);

    /* 결제창 -> 메인(주문 테이블 삽입) */
    public void order(OrderDTO orderDto);

    /* ordernumber 가져오기 */
    public int ordernumber22();

    /* 결제창 -> 메인(주문 상세 테이블 삽입) */
    public void orderDetailDTO(OrderDetailDTO orderDetailDto);

    /* 결제창 -> 메인(결제 테이블 삽입) */
    public void paymentDTO(PaymentDTO paymentDto);

    /* 장바구니 삭제 */
    public void deletecart(int checkNum);

    /* 주문목록(관리자) 선택 삭제 */
    public void deleteOrderlist(int checkNum);

    /* 주문목록(관리자) 목록 */
    public List<OrderDTO> plist();

    public void cartOrderDelete(int cartNumber);
}
