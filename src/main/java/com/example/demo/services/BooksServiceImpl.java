package com.example.demo.services;

import com.example.demo.repository.MockedBookRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImpl implements BookService{

    @Override
    public String getBooksInfo(MockedBookRepositoryImpl mockedBookRepository) {
        StringBuilder result = new StringBuilder();

        for (Object book : mockedBookRepository.findAll()) {
            result.append(book.toString());
        }
        return result.toString();
    }
}