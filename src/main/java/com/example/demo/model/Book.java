package com.example.demo.model;

import com.example.demo.model.util.Language;

import java.time.LocalDate;

public class Book {

    private String title;
    private String author;
    private LocalDate releaseDate;
    private int numberOfPages;
    private Language language;

    public Book() {
    }

    public Book(String title, String author, LocalDate releaseDate, int numberOfPages, Language language) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.numberOfPages = numberOfPages;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getLanguage() {
        return language.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}