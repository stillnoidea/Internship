package com.example.demo.controller;

import com.example.demo.services.StringGeneratorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringController {

    @RequestMapping("/start")
    public @ResponseBody
    String start() {
        return StringGeneratorService.getText();
    }
}