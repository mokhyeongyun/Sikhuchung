package com.sikhuchung.domain;

import lombok.Data;

@Data
public class ProductVO {
    private int productNumber;
    private String productCategory;
    private String productName;
    private int productPrice;
    private String productOrigin;
    private String productDelivery;
    private int productStock;
    private String productInfo;
    private String productDelete;
    private String productThumbnail;
    private String productRegister_date;
    private String productRegister;
    private String productUpdate;
    private String productUpdater;
}