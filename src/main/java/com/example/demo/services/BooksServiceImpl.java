package com.example.demo.services;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BooksServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    BooksServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksContainingWord(String word) {
        Stream<Book> books = booksContainingWord(word);
        return books.collect(Collectors.toList());
    }

    private Stream<Book> booksContainingWord(String word) {
        Stream<Book> bookStream = findAll().stream();
        return bookStream.filter(book -> isBookContainingWord(book, word));
    }

    private boolean isBookContainingWord(Book book, String word) {
        String bookInfo = bindBookFields(book);
        return bookInfo.contains(word);
    }

    private String bindBookFields(Book book) {
        return book.getAuthor() +
                book.getLanguage() +
                book.getTitle();
    }
}