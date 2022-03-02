package com.sikhuchung.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CartVO {
    private int cartNumber, productNumber, cartCount, productPrice;
    private String userId, cartIndate, productName, productThumbnail;

    public int getPriceAmount() {
        return cartCount * productPrice;
    }

}