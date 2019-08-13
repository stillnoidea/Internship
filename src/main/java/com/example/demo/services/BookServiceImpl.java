package com.example.demo.services;

import com.example.demo.dto.BookFilter;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
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

    public List<Book> findByWord(String word) {
        return filterBooksContainingWord(word).collect(Collectors.toList());
    }

    private Stream<Book> filterBooksContainingWord(String word) {
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
            return book.getAuthor().toLowerCase().contains(author.toLowerCase());
        }
        return true;
    }

    private boolean isTitleMatchingWord(Book book, String title) {
        if (title != null) {
            return book.getTitle().toLowerCase().contains(title.toLowerCase());
        }
        return true;
    }

    private boolean isLanguageMatchingWord(Book book, String language) {
        if (language != null) {
            return book.getLanguage().toLowerCase().contains(language.toLowerCase());
        }
        return true;
    }

    public List<Book> findByParams(BookFilter bookParams) {
        if (bookParams==null || isBookFilterEmpty(bookParams)) {
            throw new RuntimeException("There must be at least one parameter");
        }
        return filterBooksWithParams(bookParams).collect(Collectors.toList());
    }

    private boolean isBookFilterEmpty(BookFilter bookParams) {
        return (bookParams.getTitle() == null && bookParams.getAuthor() == null && bookParams.getLanguage() == null);
    }

    private Stream<Book> filterBooksWithParams(BookFilter bookParams) {
        Stream<Book> bookStream = getBooksStream();
        return bookStream.filter(book -> isTitleMatchingWord(book, bookParams.getTitle())
                && isAuthorMatchingWord(book, bookParams.getAuthor())
                && isLanguageMatchingWord(book, bookParams.getLanguage()));
    }
}