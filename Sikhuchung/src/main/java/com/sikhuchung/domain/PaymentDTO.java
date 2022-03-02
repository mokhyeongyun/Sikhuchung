package com.sikhuchung.domain;

import lombok.Data;

@Data
public class PaymentDTO {
    private int paymentNumber;
    private int orderNumber;
    private String paymentDelivery;
    private String paymentDate;
    private String paymentMethod;
    private int paymentTotalPrice;
    private int paymentDiscountPrice;
    private int paymentDeliveryPrice;
    private int paymentFinalPrice;
    private String paymentDepositName;
    private String paymentDepositBank;
    private String paymentAccountNumber;
}
