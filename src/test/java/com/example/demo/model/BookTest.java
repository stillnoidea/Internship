package com.example.demo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void setAuthorTest() {
        Book book = new Book();
        book.setAuthor("James Williams");
        assertEquals("James Williams", book.getAuthor());
    }
}