package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringController {
    private final StringGenerator stringGenerator;

    StringController(StringGenerator stringGenerator) {
        this.stringGenerator = stringGenerator;
    }

    @RequestMapping("/start")
    public @ResponseBody String start() {
        return stringGenerator.getText();
    }
}

