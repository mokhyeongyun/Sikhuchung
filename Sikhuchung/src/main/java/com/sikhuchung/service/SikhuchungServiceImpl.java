package com.sikhuchung.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.domain.ProductVO;
import com.sikhuchung.mapper.SikhuchungMapper;

@Service
public class SikhuchungServiceImpl implements SikhuchungService {

    @Autowired
    private SikhuchungMapper sikhuchungMapper;

    @Override
    public boolean registerService(NoticeDTO params) {
        int queryResult = 0;

        if (params.getNoticeNumber() == 0) {
            queryResult = sikhuchungMapper.insertNotice(params);
        } else {
            queryResult = sikhuchungMapper.updateNotice(params);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public NoticeDTO getNoticeDetail(int noticeNumber) {
        return sikhuchungMapper.selectNoticeDetail(noticeNumber);
    }

    @Override
    public boolean deleteNotice(int noticeNumber) {
        int queryResult = 0;

        NoticeDTO notice = sikhuchungMapper.selectNoticeDetail(noticeNumber);

        if (notice != null && "N".equals(notice.getNoticeDelete())) {
            queryResult = sikhuchungMapper.deleteNotice(noticeNumber);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<NoticeDTO> getNoticeList() {
        List<NoticeDTO> noticeList = Collections.emptyList();

        int noticeTotalCount = sikhuchungMapper.selectNoticeTotalCount();

        if (noticeTotalCount > 0) {
            noticeList = sikhuchungMapper.selectNoticeList();
        }

        return noticeList;
    }
    
    // 메인 화면 -- 재훈
    @Override
    public List<ProductVO> getProductList() {
    	List<ProductVO> productList = sikhuchungMapper.selectProductList();
    	return productList;
    }
    
    // 메인화면 제철, 간편, 별난, 선물 -- 재훈
    @Override
    public List<ProductVO> getPresentList() {
    	List<ProductVO> productList = sikhuchungMapper.selectPresentList();
    	return productList;
    }
    @Override
    public List<ProductVO> getSimpleList() {
    	List<ProductVO> productList = sikhuchungMapper.selectSimpleList();
    	return productList;
    }
    @Override
    public List<ProductVO> getSeasonList() {
    	List<ProductVO> productList = sikhuchungMapper.selectSeasonList();
    	return productList;
    }
    @Override
    public List<ProductVO> getWeirdList() {
    	List<ProductVO> productList = sikhuchungMapper.selectWeirdList();
    	return productList;
    }
    
    // 상세화면 -- 재훈
    @Override
    public ProductVO getProductData(int productNumber) {
    	ProductVO productData = sikhuchungMapper.selectProductData(productNumber);
    	return productData;
    }
}
