package com.sikhuchung.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderVO {
    private int orderNumber;
    private String userId, orderAddress1, orderAddress2, orderAddress3, orderRecipientName, orderRecipientTel,
            orderDate;
}
