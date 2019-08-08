package com.example.demo.model.util;

public enum Language {
    ENGLISH("English"),
    POLISH("Polish"),
    SWEDISH("Swedish"),
    AMERICAN("American"),
    BELGIAN("Belgian"),
    DUTCH("Dutch"),
    GERMAN("German"),
    GREEK("Greek"),
    FRENCH("French"),
    FINNISH("Finnish"),
    ITALIAN("Italian"),
    RUSSIAN("Russian"),
    SPANISH("Spanish"),
    TURKISH("Turkish");

    private String name;

    Language(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}