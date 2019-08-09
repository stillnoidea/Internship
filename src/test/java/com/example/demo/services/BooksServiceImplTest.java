package com.example.demo.services;

import com.example.demo.model.Book;
import com.example.demo.model.util.Language;
import com.example.demo.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
    private List<Book> books;

    @Before
    public void setUp() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", LocalDate.of(1997, 6, 26), 223, Language.ENGLISH));
        list.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", LocalDate.of(1926, 2, 10), 218, Language.AMERICAN));
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

    @Test
    public void shouldNotBeEmpty() {
        books = service.findBooksContainingWord("a");
        assertFalse(books.isEmpty());
    }

    @Test
    public void shouldContainBookObject() {
        books = service.findBooksContainingWord("a");
        assertEquals(Book.class, books.get(0).getClass());
    }
}