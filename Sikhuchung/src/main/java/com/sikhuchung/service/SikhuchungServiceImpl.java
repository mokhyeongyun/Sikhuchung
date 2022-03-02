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

    @Override
    public boolean hitPlus(Long noticeNumber) {
        return sikhuchungMapper.hitPlus(noticeNumber);
    }

    /* 후기 */

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
}
