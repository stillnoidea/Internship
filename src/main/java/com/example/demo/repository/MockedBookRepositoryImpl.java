package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockedBookRepositoryImpl implements BookRepository {

    private ArrayList<Book> books;

    MockedBookRepositoryImpl(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public List findAll() {
        return books;
    }

}
