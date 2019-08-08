package com.example.demo.repository;

import com.example.demo.model.Book;
import com.example.demo.model.util.Language;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MockedBookRepositoryImpl implements BookRepository {

    private ArrayList<Book> books;

    MockedBookRepositoryImpl() {
        this.books = new ArrayList<>(15);
        initializeBooks();
    }

    @Override
    public List findAll() {
        return books;
    }

    private void initializeBooks() {
        books.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", LocalDate.of(1997, 6, 26), 223, Language.ENGLISH));
        books.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", LocalDate.of(1997, 6, 26), 223, Language.ENGLISH));
        books.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", LocalDate.of(1997, 6, 26), 223, Language.ENGLISH));
        books.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", LocalDate.of(1997, 6, 26), 223, Language.ENGLISH));
        books.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", LocalDate.of(1997, 6, 26), 223, Language.ENGLISH));
    }
}
