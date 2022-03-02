package com.sikhuchung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sikhuchung.domain.CartVO;
import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.OrderDTO;
import com.sikhuchung.domain.OrderDetailDTO;
import com.sikhuchung.domain.PaymentDTO;
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

    public boolean hitPlus(Long noticeNumberLong);

    /* 후기 */
    public int selectNoticeTotalCount();

    /* 회원가입 */
    public void saveUser(UserVO userVO);

    /* id중복확인 */
    public int idCheck(String userId);

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
}
