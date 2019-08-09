package com.example.demo.model;

import com.example.demo.model.util.Language;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class BookTest {

    private Book book;
    private String bookInfo;

    @Before
    public void setUp() {
        book = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling",
                LocalDate.of(1997, 6, 26), 223, Language.ENGLISH);
        bookInfo = book.toString();
    }

    @Test
    public void shouldContainsDefaultWords() {
        assertTrue(bookInfo.contains("title"));
        assertTrue(bookInfo.contains("author"));
        assertTrue(bookInfo.contains("releaseDate"));
        assertTrue(bookInfo.contains("numberOfPages"));
        assertTrue(bookInfo.contains("language"));
    }

    @Test
    public void shouldContainCurlyBracket() {
        assertTrue(bookInfo.contains("{"));
        assertTrue(bookInfo.contains("}"));
    }

    @Test
    public void shouldContainColon() {
        assertTrue(bookInfo.contains(":"));
    }
}