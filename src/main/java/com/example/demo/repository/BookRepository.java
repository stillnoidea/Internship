package com.example.demo.repository;

import com.example.demo.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
}
