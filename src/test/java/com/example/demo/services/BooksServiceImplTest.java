package com.example.demo.services;

import com.example.demo.dto.BookMainInfo;
import com.example.demo.model.Book;
import com.example.demo.model.util.Language;
import com.example.demo.repository.BookRepository;
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

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BooksServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMainInfo bookMainInfo;
    @InjectMocks
    private BookServiceImpl service;
    private List<Book> books;

    @Test
    public void shouldBeNotEmpty() {
        Mockito.when(bookRepository.findAll()).thenReturn(initializeBooksListMock());
        books = service.findAll();
        assertFalse(books.isEmpty());
    }

    @Test
    public void shouldContainsTwentyOneBooks() {
        Mockito.when(bookRepository.findAll()).thenReturn(initializeBooksListMock());
        books = service.findAll();
        assertEquals(2, books.size());
    }

    @Test
    public void shouldNotBeEmpty() {
        Mockito.when(service.findBooksContainingWord("a")).thenReturn(initializeBooksListMock());
        books = service.findBooksContainingWord("a");
        assertFalse(books.isEmpty());
    }

    @Test
    public void shouldContainBookObject() {
        Mockito.when(service.findBooksContainingWord("a")).thenReturn(initializeBooksListMock());
        books = service.findBooksContainingWord("a");
        assertEquals(Book.class, books.get(0).getClass());
    }

    private List<Book> initializeBooksListMock() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", LocalDate.of(1997, 6, 26), 223, Language.ENGLISH));
        list.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", LocalDate.of(1926, 2, 10), 218, Language.AMERICAN));
        return list;
    }

    @Test
    public void shouldReturnNull() {
        assertNull(service.findBooksWithParams(bookMainInfo));
    }

    @Test
    public void shouldReturnEmptyList() {
        Mockito.when(bookMainInfo.getAuthor()).thenReturn("Carl");
        Mockito.when(service.findAll()).thenReturn(initializeBooksListMock());
        assertTrue(service.findBooksWithParams(bookMainInfo).isEmpty());
    }

    @Test
    public void shouldReturnOneElementList() {
        Mockito.when(bookMainInfo.getAuthor()).thenReturn("Rowling");
        Mockito.when(service.findAll()).thenReturn(initializeBooksListMock());
        List<Book> books = service.findBooksWithParams(bookMainInfo);
        assertEquals(1, books.size());
        assertEquals("J.K. Rowling", books.get(0).getAuthor());
        assertEquals(223, books.get(0).getNumberOfPages());
    }

    @Test
    public void shouldReturnTwoElementList() {
        Mockito.when(bookMainInfo.getTitle()).thenReturn("a");
        Mockito.when(service.findAll()).thenReturn(initializeBooksListMock());
        List<Book> books = service.findBooksWithParams(bookMainInfo);
        assertEquals(2, books.size());
        assertEquals("J.K. Rowling", books.get(0).getAuthor());
        assertEquals(223, books.get(0).getNumberOfPages());
        assertEquals("F. Scott Fitzgerald", books.get(1).getAuthor());
        assertEquals(218, books.get(1).getNumberOfPages());
    }

    @Test
    public void shouldReturnHarryPotter() {
        Mockito.when(bookMainInfo.getTitle()).thenReturn("a");
        Mockito.when(bookMainInfo.getAuthor()).thenReturn("Row");
        Mockito.when(service.findAll()).thenReturn(initializeBooksListMock());
        List<Book> books = service.findBooksWithParams(bookMainInfo);
        assertEquals(1, books.size());
        assertEquals("J.K. Rowling", books.get(0).getAuthor());
        assertEquals(223, books.get(0).getNumberOfPages());
    }
}