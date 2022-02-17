package com.sikhuchung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sikhuchung.service.SikhuchungService;

@Controller
public class SikhuchungController {

    @Autowired
    private SikhuchungService sikhuchungService;

    @GetMapping(value = "/sikhuchung/sikhuchungTest.do")
    public String sikhuchungTest() {

        return "sikhuchung/sikhuchungTest";
    }

}
