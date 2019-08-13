package com.example.demo.services;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.dto.BookMainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksContainingWord(String word) {
        return booksContainingWord(word).collect(Collectors.toList());
    }

    private Stream<Book> booksContainingWord(String word) {
        Stream<Book> bookStream = getBooksStream();
        return bookStream.filter(book -> isBookContainingWord(book, word));
    }

    private Stream<Book> getBooksStream() {
        return findAll().stream();
    }

    private boolean isBookContainingWord(Book book, String word) {
        return isAuthorMatchingWord(book, word) || isTitleMatchingWord(book, word) || isLanguageMatchingWord(book, word);
    }

    private boolean isAuthorMatchingWord(Book book, String author) {
        if (author != null) {
            return book.getAuthor().contains(author);
        } else return true;
    }

    private boolean isTitleMatchingWord(Book book, String title) {
        if (title != null) {
            return book.getTitle().contains(title);
        } else return true;
    }

    private boolean isLanguageMatchingWord(Book book, String language) {
        if (language != null) {
            return book.getLanguage().contains(language);
        } else return true;
    }

    public List<Book> findBooksWithParams(BookMainInfo bookParams) {
        if (bookParams.getTitle() == null && bookParams.getAuthor() == null && bookParams.getLanguage() == null) {
            return null;
        }
        return findBooksWithAllParams(bookParams);
    }

    private List<Book> findBooksWithAllParams(BookMainInfo bookParams) {
        return booksWithParams(bookParams).collect(Collectors.toList());
    }

    private Stream<Book> booksWithParams(BookMainInfo bookParams) {
        Stream<Book> bookStream = getBooksStream();
        return bookStream.filter(book -> isTitleMatchingWord(book, bookParams.getTitle())
                && isAuthorMatchingWord(book, bookParams.getAuthor())
                && isLanguageMatchingWord(book, bookParams.getLanguage()));
    }
}