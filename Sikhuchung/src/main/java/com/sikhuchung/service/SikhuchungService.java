package com.sikhuchung.service;

import java.util.List;

import com.sikhuchung.domain.CartVO;
import com.sikhuchung.domain.MailDto;
import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.OrderDTO;
import com.sikhuchung.domain.OrderDetailDTO;
import com.sikhuchung.domain.PaymentDTO;
import com.sikhuchung.domain.ProductVO;
import com.sikhuchung.domain.ReviewDTO;
import com.sikhuchung.domain.UserVO;

public interface SikhuchungService {

    /* 공지사항 */
    public boolean registerNotice(NoticeDTO params);

    public NoticeDTO getNoticeDetail(Long noticeNumber);

    public boolean deleteNotice(Long noticeNumber);

    public List<NoticeDTO> getNoticeList(NoticeDTO params);

    /* 메인화면 -- 재훈 */
    public List<ProductVO> getProductList();

    /* 메인화면 제철, 간편, 별난, 선물 -- 재훈 */
    public List<ProductVO> getPresentList();

    public List<ProductVO> getSimpleList();

    public List<ProductVO> getSeasonList();

    public List<ProductVO> getWeirdList();

    /* 상세화면 -- 재훈 */
    public ProductVO getProductData(int productNumber);

    public List<ReviewDTO> getDetailReviewList(int productNumber);

    /* 장바구니 넣기 -- 재훈 */
    public void getItem(CartVO cartvo);

    public boolean hitPlus(Long noticeNumber);

    /* 후기 */
    public void registerReview(ReviewDTO reviewdto);

    public void reviewUpdate(ReviewDTO reviewdto);

    public void updateresult(Long result);

    public ReviewDTO getReviewDetail(Long reviewNumber);

    public boolean deleteReview(Long reviewNumber);

    public List<ReviewDTO> getReviewList();

    public String getProductName(int orderDetailNumber);

    public ReviewDTO getreviewdto(int orderDetailNumber);

    public void reviewDelete(int orderDetailNumber);

    public void resultChange(int orderDetailNumber);

    /* 회원가입 */
    public void joinUser(UserVO userVO);

    // 아이디 중복 검사
    public int idCheck(String userId) throws Exception;

    // 로그인
    public int userLogin(UserVO userVO) throws Exception;

    // 아이디찾기
    public String findIdCheck(UserVO userVO) throws Exception;

    /* 비밀번호찾기 (이메일확인) */
    public boolean userEmailCheck(String userEmail, String userName);

    /* 비밀번호찾기 (메일세팅) */
    public MailDto createMailAndChangePassword(String userEmail, String userName);

    /* 비밀번호찾기 (임시비밀번호변경) */
    public void updatePassword(String str, String userEmail);

    /* 비밀번호찾기 (임시비밀번호 세팅) */
    public String getTempPassword();

    /* 비밀번호찾기 (메일보내기) */
    public void mailSend(MailDto mailDto);

    // 마이페이지 회원정보수정 비밀번호체크
    public int memberInfoPwCheck(UserVO userVO) throws Exception;

    // 마이페이지 회원정보수정 보여주기
    public UserVO memberInfo(UserVO userVO);

    // 회원정보변경
    public void memberInfoUpdate(UserVO userVO);

    // 회원탈퇴
    public void memberQuit(UserVO userVO) throws Exception;

    public int[] getOrderNumber(String userId) throws Exception;

    public void deleteCart2(String userId);

    public int[] getOrderDetailNumber(int orderNumber);

    public void deleteReview2(int orderDetailNumber);

    public void deleteOrderDetail(int orderDetailNumber);

    public void deletePayment(int orderNumber);

    public void deleteOrder(int orderNumber);

    // 마이페이지 주문목록
    public List<OrderDetailDTO> getOrderList(String user) throws Exception;

    /* 장바구니 - 필립 */
    public List<CartVO> cartlist(String userid) throws Exception;

    /* 장바구니 -> 결제창 - 필립 */
    public CartVO paymentlist(String paymentlist) throws Exception;

    /* 결제창 -> 메인 주문 테이블 삽입 */
    public void order(OrderDTO orderDto) throws Exception;

    /* ordernumber 가져오기 */
    public int ordernumber22() throws Exception;

    /* 결제창 -> 메인 주문 상세 테이블 삽입 */
    public void orderDetailDTO(OrderDetailDTO orderDetailDto) throws Exception;

    /* 결제창 -> 메인 주문 결제 테이블 삽입 */
    public void paymentDTO(PaymentDTO paymentDto) throws Exception;

    /* 장바구니 선택 삭제 - 필립 */
    public void deletecart(int checkNum) throws Exception;

    /* 주문목록(관리자) 선택 삭제 - 필립 */
    public void deleteOrderlist1(int checkNum) throws Exception;

    public void deleteOrderlist2(int checkNum) throws Exception;

    /* 주문목록 입금 상태 변경 - 필립 */
    public void changeDeposit(int checkNum) throws Exception;

    /* 주문목록(관리자) - 필립 */
    public List<OrderDTO> plist() throws Exception;

    public void cartOrderDelete(int cartNumber) throws Exception;

}
