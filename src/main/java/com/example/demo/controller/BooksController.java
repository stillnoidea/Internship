package com.example.demo.controller;

import com.example.demo.services.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    List getLibrary() {
        return service.findAll();
    }

    @RequestMapping("/search")
    public @ResponseBody
    List search(@RequestParam(name = "word") String soughtWord) {
        return service.findBooksContainingWord(soughtWord);
    }
}