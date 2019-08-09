package com.example.demo.controller;

import com.example.demo.services.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BooksController {

    private final BooksServiceImpl service;

    @Autowired
    public BooksController(BooksServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/library")
    public @ResponseBody List start() {
        return service.getBooksInfo();
    }

    @RequestMapping("/search")
    public @ResponseBody List search(@RequestParam String search) {
        List result = new ArrayList();
        List allBooks = service.getBooksInfo();

        for (Object book : allBooks) {
            if (isBookContainingSearchedWord(book, search)) {
                result.add(book);
            }
        }
        return result;
    }

    private boolean isBookContainingSearchedWord(Object book, String searchedWord) {
        return book.toString().contains(searchedWord);
    }
}