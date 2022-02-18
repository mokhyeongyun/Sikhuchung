package com.sikhuchung.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDTO {
    int noticeNumber, noticeHit;
    String userId, noticeTitle, noticeContent, noticeDate, noticeUpdateId, noticeUpdateDate, noticeDelete;
}
