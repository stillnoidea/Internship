package com.example.demo.services;

import com.example.demo.repository.MockedBookRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksServiceImplTest {
    @Autowired
    private MockedBookRepositoryImpl mockedBookRepository;
    @Autowired
    private BooksServiceImpl service;

    @Test
    public void shouldContainsCurlyBrackets() {
        String booksInfo = service.getBooksInfo(mockedBookRepository);
        assertTrue(booksInfo.contains("{"));
        assertTrue(booksInfo.contains("}"));
    }

    @Test
    public void shouldContainsColon() {
        String booksInfo = service.getBooksInfo(mockedBookRepository);
        assertTrue(booksInfo.contains(":"));
    }

    @Test
    public void shouldContainsDefaultWords() {
        String booksInfo = service.getBooksInfo(mockedBookRepository);
        assertTrue(booksInfo.contains("title"));
        assertTrue(booksInfo.contains("author"));
        assertTrue(booksInfo.contains("releaseDate"));
        assertTrue(booksInfo.contains("numberOfPages"));
        assertTrue(booksInfo.contains("language"));
    }
}