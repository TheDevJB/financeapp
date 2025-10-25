package com.financeapp.finance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public/hello")
    public String publicEndpoint(){
        return "this is public no auth";
    }
    
    @GetMapping("/protected/hello")
    public String protectedEndpoint(){
        return "this is protected need auth";
    }

}
