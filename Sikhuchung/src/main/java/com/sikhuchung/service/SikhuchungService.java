package com.sikhuchung.service;

import java.util.List;

import com.sikhuchung.domain.NoticeDTO;

public interface SikhuchungService {
    public boolean registerService(NoticeDTO params);

    public NoticeDTO getNoticeDetail(int noticeNumber);

    public boolean deleteNotice(int noticeNumber);

    public List<NoticeDTO> getNoticeList();

}
