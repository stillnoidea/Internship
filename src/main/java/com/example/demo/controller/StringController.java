package com.example.demo.controller;

import com.example.demo.services.StringGeneratorService;
import com.example.demo.services.StringGeneratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringController {

    private final StringGeneratorService service;

    @Autowired
    public StringController(StringGeneratorServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/start")
    public @ResponseBody String start() {
        return service.getText();
    }
}