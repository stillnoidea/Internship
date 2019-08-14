package com.example.demo.services;

import com.example.demo.dto.BookFilter;
import com.example.demo.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    List<Book> findByWord(String word);

    List<Book> findByParams(BookFilter bookParams);
}
