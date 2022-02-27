package com.sikhuchung.service;

import java.util.List;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.ProductVO;

public interface SikhuchungService {
    public boolean registerService(NoticeDTO params);

    public NoticeDTO getNoticeDetail(int noticeNumber);

    public boolean deleteNotice(int noticeNumber);

    public List<NoticeDTO> getNoticeList();
    
    // 메인화면 -- 재훈
    public List<ProductVO> getProductList();
    
    // 메인화면 제철, 간편, 별난, 선물 -- 재훈
    public List<ProductVO> getPresentList();
    public List<ProductVO> getSimpleList();
    public List<ProductVO> getSeasonList();
    public List<ProductVO> getWeirdList(); 
    
    // 상세화면 -- 재훈
    public ProductVO getProductData(int productNumber);

}
