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

    @Override
    public List<Book> findByWord(String word) {
        return filterBooksContainingWord(word).collect(Collectors.toList());
    }

    private Stream<Book> filterBooksContainingWord(String word) {
        Stream<Book> bookStream = findAll().stream();
        return bookStream.filter(book -> isBookContainingWord(book, word));
    }

    private boolean isBookContainingWord(Book book, String word) {
        return isBookInfoContainingWord(book.getAuthor(), word) || isBookInfoContainingWord(book.getTitle(), word) || isBookInfoContainingWord(book.getLanguage(), word);
    }

    private boolean isBookInfoContainingWord(String book, String author) {
        if (author != null) {
            return book.toLowerCase().contains(author.toLowerCase());
        }
        return true;
    }

    @Override
    public List<Book> findByParams(BookFilter bookParams) {
        if (bookParams == null || isBookFilterEmpty(bookParams)) {
            throw new RuntimeException("There must be at least one parameter");
        }
        return filterBooksWithParams(bookParams).collect(Collectors.toList());
    }

    private boolean isBookFilterEmpty(BookFilter bookParams) {
        return (bookParams.getTitle() == null && bookParams.getAuthor() == null && bookParams.getLanguage() == null);
    }

    private Stream<Book> filterBooksWithParams(BookFilter bookParams) {
        Stream<Book> bookStream = findAll().stream();
        return bookStream.filter(book -> isBookInfoContainingWord(book.getTitle(), bookParams.getTitle())
                && isBookInfoContainingWord(book.getAuthor(), bookParams.getAuthor())
                && isBookInfoContainingWord(book.getLanguage(), bookParams.getLanguage()));
    }
}