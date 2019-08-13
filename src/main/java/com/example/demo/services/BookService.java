package com.example.demo.services;

import com.example.demo.dto.BookMainInfo;
import com.example.demo.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    List<Book> findBooksContainingWord(String word);

    List<Book> findBooksWithParams(BookMainInfo bookParams);
}
