package com.sikhuchung.service;

import java.util.List;

import com.sikhuchung.domain.NoticeDTO;

public interface SikhuchungService {
    /* 공지사항 */
    public boolean registerNotice(NoticeDTO params);

    public NoticeDTO getNoticeDetail(Long noticeNumber);

    public boolean deleteNotice(Long noticeNumber);

    public List<NoticeDTO> getNoticeList();

    /* 후기 */

}
