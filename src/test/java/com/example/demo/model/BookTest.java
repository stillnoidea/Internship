package com.example.demo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void setAuthorTest() {
        // given
        Book book = new Book();

        // when
        book.setAuthor("James Williams");

        // then
        assertEquals("James Williams", book.getAuthor());
    }
}