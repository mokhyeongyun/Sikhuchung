package com.sikhuchung.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sikhuchung.domain.CartVO;
import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.OrderDTO;
import com.sikhuchung.domain.OrderDetailDTO;
import com.sikhuchung.domain.PaymentDTO;
import com.sikhuchung.domain.ProductVO;
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
    /* 회원가입 --현균 */
    @Transactional
    @Override
    public void joinUser(UserVO userVO) {
        userVO.setUserGrade("USER");
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

    // 마이페이지 회원정보수정 비밀번호체크
    @Override
    public int memberInfoPwCheck(UserVO userVO) throws Exception {
        return sikhuchungMapper.memberInfoPwCheck(userVO);
    }

    // 마이페이지 회원정보수정 보여주기
    @Override
    public UserVO memberInfo(UserVO userVO) {
        return sikhuchungMapper.memberInfo(userVO);
    }

    // 마이페이지 회원정보수정 진행
    @Override
    public void memberInfoUpdate(UserVO userVO) {
        sikhuchungMapper.memberInfoUpdate(userVO);
    }

    // 마이페이지 회원탈퇴 진행
    @Override
    public void memberQuit(UserVO userVO) throws Exception {
        sikhuchungMapper.memberQuit(userVO);
    }

    // 마이페이지 주문목록
    @Override
    public List<OrderDetailDTO> getOrderList(String user) throws Exception {
        return sikhuchungMapper.getOrderList(user);
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
    
    /* 장바구니 넣기 -- 재훈 */
    @Override
    public void getItem(CartVO cartvo) {
    	sikhuchungMapper.item(cartvo);
    }

    /* 장바구니 */
    @Override
    public List<CartVO> cartlist(String userid) {
        return sikhuchungMapper.cartlist(userid);
    }

    /* 결제창 */
    @Override
    public CartVO paymentlist(String paymentlist) {
        return sikhuchungMapper.paymentlist(paymentlist);
    }

    /* 주문목록 -> 메인(주문 테이블 삽입) */
    @Override
    public void order(OrderDTO orderDto) {
        sikhuchungMapper.order(orderDto);
    }

    /* ordernumber 가져오기 */
    @Override
    public int ordernumber22() {
        return sikhuchungMapper.ordernumber22();
    }

    /* 주문목록 -> 메인(주문 상세 테이블 삽입) */
    @Override
    public void orderDetailDTO(OrderDetailDTO orderDetailDto) {
        sikhuchungMapper.orderDetailDTO(orderDetailDto);
    }

    /* 주문목록 -> 메인(결제 테이블 삽입) */
    @Override
    public void paymentDTO(PaymentDTO paymentDto) {
        sikhuchungMapper.paymentDTO(paymentDto);
    }

    /* 장바구니 선택 삭제 */
    @Override
    public void deletecart(int checkNum) {
        sikhuchungMapper.deletecart(checkNum);
    }

    /* 주문목록(관리자) 선택 삭제 */
    @Override
    public void deleteOrderlist(int checkNum) {
        sikhuchungMapper.deleteOrderlist(checkNum);
    }

    /* 주문목록(관리자) */
    @Override
    public List<OrderDTO> plist() {
        return sikhuchungMapper.plist();
    }

    @Override
    public void cartOrderDelete(int cartNumber) throws Exception {
        sikhuchungMapper.cartOrderDelete(cartNumber);
    }
}
