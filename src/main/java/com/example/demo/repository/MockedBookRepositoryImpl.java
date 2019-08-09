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
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", LocalDate.of(1926, 2, 10), 218, Language.AMERICAN));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", LocalDate.of(1960, 7, 11), 281, Language.AMERICAN));
        books.add(new Book("1984", "George Orwell", LocalDate.of(1949, 6, 8), 328, Language.ENGLISH));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", LocalDate.of(1951, 7, 16), 277, Language.AMERICAN));
        books.add(new Book("The Hobbit, or There and Back Again", "J. R. R. Tolkien", LocalDate.of(1937, 9, 21), 310, Language.ENGLISH));
        books.add(new Book("Fahrenheit 451", "Ray Bradbury", LocalDate.of(1953, 9, 19), 158, Language.ENGLISH));
        books.add(new Book("Pride and Prejudice", "Jane Austen", LocalDate.of(1813, 2, 28), 279, Language.ENGLISH));
        books.add(new Book("Brave New World", "Aldous Huxley", LocalDate.of(1932, 2, 12), 311, Language.ENGLISH));
        books.add(new Book("A Wrinkle in Time", "Madeleine L'Engle", LocalDate.of(1962, 2, 1), 218, Language.GERMAN));
        books.add(new Book("The Little Prince", "Antoine de Saint-Exupéry", LocalDate.of(1943, 4, 10), 96, Language.FRENCH));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", LocalDate.of(1866, 2, 23), 545, Language.RUSSIAN));
        books.add(new Book("The Shadow of the Wind", "Carlos Ruiz Zafón", LocalDate.of(2005, 2, 25), 487, Language.SPANISH));
        books.add(new Book("My Name Is Red", "Orhan Pamuk", LocalDate.of(1998, 9, 27), 448, Language.TURKISH));
        books.add(new Book("The Cyberiad", "Stanisław Lem", LocalDate.of(1965, 12, 16), 295, Language.POLISH));
        books.add(new Book("Zorba the Greek", "Nikos Kazantzakis", LocalDate.of(1964, 4, 3), 335, Language.GREEK));
        books.add(new Book("Tales from Moominvalley", "Tove Jansson", LocalDate.of(1962, 11, 6), 192, Language.SWEDISH));
        books.add(new Book("The Name of the Rose", "Umberto Eco", LocalDate.of(1980, 8, 28), 512, Language.ITALIAN));
        books.add(new Book("Kaas", "Willem Elsschot", LocalDate.of(1933, 2, 8), 126, Language.BELGIAN));
        books.add(new Book("Kalevala", "Elias Lönnrot", LocalDate.of(1835, 1, 20), 208, Language.FINNISH));
        books.add(new Book("The Discovery of Heaven", "Harry Mulisch", LocalDate.of(1992, 9, 18), 126, Language.DUTCH));
    }
}