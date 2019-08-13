package com.example.demo.dto;

public class BookFilter {
    private String author;
    private String title;
    private String language;

    public BookFilter(String author, String title, String language) {
        this.author = author;
        this.title = title;
        this.language = language;
    }

    public BookFilter() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}