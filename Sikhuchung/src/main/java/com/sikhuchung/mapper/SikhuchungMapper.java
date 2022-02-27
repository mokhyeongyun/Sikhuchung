package com.sikhuchung.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.ProductVO;

@Mapper
public interface SikhuchungMapper {
    public int insertNotice(NoticeDTO params);

    public NoticeDTO selectNoticeDetail(int noticeNumber);

    public int updateNotice(NoticeDTO params);

    public int deleteNotice(int noticeNumber);

    public List<NoticeDTO> selectNoticeList();

    public int selectNoticeTotalCount();
    
    // 메인화면 -- 재훈
    public List<ProductVO> selectProductList();
    
    // 메인화면 제철, 간편, 별난, 선물 -- 재훈
    public List<ProductVO> selectPresentList();
    public List<ProductVO> selectSimpleList();
    public List<ProductVO> selectSeasonList();
    public List<ProductVO> selectWeirdList();
    
    // 상세화면 -- 재훈
    public ProductVO selectProductData(int productNumber);
}
