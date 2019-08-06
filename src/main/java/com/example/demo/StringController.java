package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringController {

    @RequestMapping("/greeting")
    public StringGenerator greeting() {
        return new StringGenerator();
    }
}
