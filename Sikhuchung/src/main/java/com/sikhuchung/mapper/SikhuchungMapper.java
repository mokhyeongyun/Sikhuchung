package com.sikhuchung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sikhuchung.domain.NoticeDTO;

@Mapper
public interface SikhuchungMapper {
    /* 공지사항 */
    public int insertNotice(NoticeDTO params);

    public NoticeDTO selectNoticeDetail(Long noticeNumber);

    public int updateNotice(NoticeDTO params);

    public int deleteNotice(Long noticeNumber);

    public List<NoticeDTO> selectNoticeList();

    public int selectNoticeTotalCount();

    /* 후기 */

}
