package com.sikhuchung.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private Long reviewNumber, orderDetailNumber;
    private String userId, reviewTitle, reviewContent, reviewDate, reviewRate, reviewUpdateDate, reviewDelete;
}
