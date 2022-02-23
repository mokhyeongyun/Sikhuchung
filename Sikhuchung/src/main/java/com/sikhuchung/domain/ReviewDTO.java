package com.sikhuchung.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    Long reviewNumber, orderDetailNumber;
    String reviewTitle, reviewContent, reviewDate, reviewRate, reviewUpdateDate, reviewDelete;
}
