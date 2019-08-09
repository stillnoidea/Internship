package com.example.demo.services;

import com.example.demo.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BooksServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BooksServiceImpl service;
    private List books;

    @Before
    public void setUp() {
        List list = new ArrayList<>();
        list.add(12);
        list.add(1);
        Mockito.when(bookRepository.findAll()).thenReturn(list);
        books = service.findAll();
    }

    @Test
    public void shouldBeNotEmpty() {
        assertFalse(books.isEmpty());
    }

    @Test
    public void shouldContainsTwentyOneBooks() {
        assertEquals(2, books.size());
    }
}