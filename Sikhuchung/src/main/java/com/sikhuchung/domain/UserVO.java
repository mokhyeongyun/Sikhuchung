package com.sikhuchung.domain;

import lombok.Data;

@Data
public class UserVO {

    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_email;
    private String user_tel;
    private String user_grade;
    private String user_joindate;

}
