package com.example.demo.controller;

import com.example.demo.services.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksController {

    private final BooksServiceImpl service;

    @Autowired
    public BooksController(BooksServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/library")
    public @ResponseBody
    List start() {
        return service.getBooksInfo();
    }
}