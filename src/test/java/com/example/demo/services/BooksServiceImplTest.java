package com.example.demo.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksServiceImplTest {
    @Autowired
    private BooksServiceImpl service;
    private List booksInfo;

    @Before
    public void setUp() {
        booksInfo = service.getBooksInfo();
    }

    @Test
    public void shouldBeNotEmpty() {
        assertFalse(booksInfo.isEmpty());
    }

    @Test
    public void shouldContainsTwentyOneBooks() {
        assertEquals(21, booksInfo.size());
    }
}