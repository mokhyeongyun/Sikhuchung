package com.sikhuchung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sikhuchung.service.SikhuchungService;

@Controller
public class SikhuchungController {

    @Autowired
    private SikhuchungService sikhuchungService;

}
