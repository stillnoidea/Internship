package com.example.demo.model;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private LocalDate releaseDate;
    private int numberOfPages;
    private Language language;

    public String getMainInfo() {
        StringBuilder mainInfo = new StringBuilder();

        mainInfo.append("Book: ")
                .append(title)
                .append("\n")
                .append("Author: ")
                .append(author);

        return mainInfo.toString();
    }

    public String getDetails() {
        StringBuilder details = new StringBuilder();

        details.append("Release date: ")
                .append(releaseDate)
                .append("\n")
                .append("Number of pages: ")
                .append(numberOfPages)
                .append("\n")
                .append("Language: ")
                .append(language.toString());

        return details.toString();
    }
}
