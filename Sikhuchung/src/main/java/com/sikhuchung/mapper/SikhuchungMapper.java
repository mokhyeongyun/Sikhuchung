package com.sikhuchung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sikhuchung.domain.NoticeDTO;

@Mapper
public interface SikhuchungMapper {
    public int insertNotice(NoticeDTO params);

    public NoticeDTO selectNoticeDetail(int noticeNumber);

    public int updateNotice(NoticeDTO params);

    public int deleteNotice(int noticeNumber);

    public List<NoticeDTO> selectNoticeList();

    public int selectNoticeTotalCount();
}
