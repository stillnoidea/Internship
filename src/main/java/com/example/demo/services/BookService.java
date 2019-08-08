package com.example.demo.services;

import com.example.demo.repository.MockedBookRepositoryImpl;

public interface BookService {

    String getBooksInfo(MockedBookRepositoryImpl mockedBookRepository);
}
