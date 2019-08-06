package com.example.demo;

import java.util.Random;

public class StringGenerator {

    private String text;
    private final static String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789";

    public StringGenerator() {
        generateText();
    }

    private void generateText() {
        int size = generateTextLength();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(getRandomChar());
        }
        text = sb.toString();
    }

    private int generateTextLength() {
        return new Random().nextInt();
    }

    private char getRandomChar() {
        int charIndex = (int) (ALPHA_NUMERIC_STRING.length() * Math.random());
        return ALPHA_NUMERIC_STRING.charAt(charIndex);
    }

    public String getText() {
        return text;
    }
}
