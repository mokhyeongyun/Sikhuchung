package com.sikhuchung.domain;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private int orderDetailNumber;
    private int productNumber;
    private int orderNumber;
    private int orderDetailCount;
    private String orderDetailResult;
    private String orderDate;
    private int productPrice;
    private String productName;

    public int getPrice() {
        return orderDetailCount * productPrice;
    }

}
