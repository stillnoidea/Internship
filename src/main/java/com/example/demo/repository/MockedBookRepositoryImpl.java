package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockedBookRepositoryImpl implements BookRepository {

    private ArrayList<Book> books;

    MockedBookRepositoryImpl() {
        this.books = new ArrayList<>(15);
    }

    @Override
    public List findAll() {
        return books;
    }

}
