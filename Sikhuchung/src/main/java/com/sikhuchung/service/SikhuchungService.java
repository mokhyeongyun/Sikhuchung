package com.sikhuchung.service;

import java.util.List;

import com.sikhuchung.domain.CartVO;
import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.OrderDTO;
import com.sikhuchung.domain.OrderDetailDTO;
import com.sikhuchung.domain.PaymentDTO;
import com.sikhuchung.domain.UserVO;

public interface SikhuchungService {
    /* 공지사항 */
    public boolean registerNotice(NoticeDTO params);

    public NoticeDTO getNoticeDetail(Long noticeNumber);

    public boolean deleteNotice(Long noticeNumber);

    public List<NoticeDTO> getNoticeList(NoticeDTO params);

    public boolean hitPlus(Long noticeNumber);

    /* 후기 */

    /* 회원가입 */
    public void joinUser(UserVO userVO);

    // 아이디 중복 검사
    public int idCheck(String userId) throws Exception;

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
    public void deleteOrderlist(int checkNum) throws Exception;

    /* 주문목록(관리자) - 필립 */
    public List<OrderDTO> plist() throws Exception;

}
