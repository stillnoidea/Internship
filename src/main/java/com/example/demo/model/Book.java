package com.example.demo.model;

import com.example.demo.model.util.Language;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

public class Book {

    private String title;
    private String author;
    private LocalDate releaseDate;
    private int numberOfPages;
    private Language language;

    public Book(String title, String author, LocalDate releaseDate, int numberOfPages, Language language) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.numberOfPages = numberOfPages;
        this.language = language;
    }

    @Override
    public String toString() {
        String result = "";

        try {
            result = writeJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String writeJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .writeValueAsString(this);
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

    public Language getLanguage() {
        return language;
    }
}