package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StringGenerator {

    private String text;
    protected final static String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789";
    protected final static int MAX_TEXT_LENGTH = 180;

    public StringGenerator() {
        text = generateText();
    }

    protected String generateText() {
        int size = generateTextLength();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(getRandomChar());
        }
        return sb.toString();
    }

    protected int generateTextLength() {
        return new Random().nextInt(MAX_TEXT_LENGTH);
    }

    protected char getRandomChar() {
        int charIndex = (int) (ALPHA_NUMERIC_STRING.length() * Math.random());
        return ALPHA_NUMERIC_STRING.charAt(charIndex);
    }

    public String getText() {
        return text;
    }
}
