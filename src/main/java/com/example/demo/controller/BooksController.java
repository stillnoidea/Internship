package com.example.demo.controller;

import com.example.demo.repository.MockedBookRepositoryImpl;
import com.example.demo.services.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    private final BooksServiceImpl service;

    @Autowired
    public BooksController(BooksServiceImpl service, MockedBookRepositoryImpl repository) {
        this.service = service;
    }

    @RequestMapping("/library")
    public @ResponseBody
    String start() {
        return service.getBooksInfo();
    }
}