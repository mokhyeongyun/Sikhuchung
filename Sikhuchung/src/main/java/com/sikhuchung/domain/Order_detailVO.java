package com.sikhuchung.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Order_detailVO {
    private int orderDetailNumber, productNumber, orderNumber, orderDetailCount;
    private String orderDetailResult;

}
