package com.example.demo.services;

import com.example.demo.repository.MockedBookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements BookService {

    private MockedBookRepositoryImpl mockedBookRepository;

    @Autowired
    BooksServiceImpl(MockedBookRepositoryImpl mockedBookRepository) {
        this.mockedBookRepository = mockedBookRepository;
    }

    @Override
    public List getBooksInfo() {
        return mockedBookRepository.findAll();
    }
}