package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringController {

    @RequestMapping("/start")
    public StringGenerator start() {
        return new StringGenerator();
    }
}

