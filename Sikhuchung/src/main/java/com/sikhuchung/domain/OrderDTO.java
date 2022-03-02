package com.sikhuchung.domain;

import lombok.Data;

@Data
public class OrderDTO {
    private int orderNumber;
    private String userId;
    private String orderAddress1;
    private String orderAddress2;
    private String orderAddress3;
    private String orderRecipientName;
    private String orderDate;
    private String orderRecipientTel;
    private String orderDetailResult;
}
