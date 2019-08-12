package com.example.demo.controller;

import com.example.demo.services.BooksServiceImpl;
import com.example.demo.dto.BookMainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
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

    @RequestMapping("/filter")
    public @ResponseBody
    List filter(BookMainInfo bookParams) {
        List result = service.findBooksWithParams(bookParams);
        if (result == null) {
            throw new InvalidParameterException("There must be at least one parameter");
        } else return result;
    }
}