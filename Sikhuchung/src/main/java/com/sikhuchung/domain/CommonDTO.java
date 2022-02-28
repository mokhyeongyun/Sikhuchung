package com.sikhuchung.domain;

import com.sikhuchung.paging.Criteria;
import com.sikhuchung.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria {

    /** 페이징 정보 */
    private PaginationInfo paginationInfo;

}
