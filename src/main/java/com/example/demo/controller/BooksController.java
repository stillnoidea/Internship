package com.example.demo.controller;

import com.example.demo.dto.BookFilter;
import com.example.demo.model.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksController {

    private final BookService service;

    @Autowired
    public BooksController(BookService service) {
        this.service = service;
    }

    @GetMapping("/library")
    public List<Book> getLibrary() {
        return service.findAll();
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam(name = "word") String soughtWord) {
        return service.findByWord(soughtWord);
    }

    @GetMapping("/filter")
    public List<Book> filter(BookFilter bookParams) {
       return service.findByParams(bookParams);
    }
}