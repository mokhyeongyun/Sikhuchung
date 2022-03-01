package com.sikhuchung.service;

import java.util.List;

import com.sikhuchung.domain.CartVO;
import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.OrderDTO;
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

    /* 결제창 -> 메인 */
    public OrderDTO orderlist(String orders) throws Exception;

    public OrderDTO orderlist1(String orders) throws Exception;

    public OrderDTO orderlist2(String orders) throws Exception;

    /* 장바구니 선택 삭제 - 필립 */
    public void deletecart(int checkNum) throws Exception;

}
