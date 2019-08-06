package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringGenerator {

    private String text;
    private final static String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789";
    private final static int MAX_TEXT_LENGTH = 180;

    public StringGenerator() {
        text = generateText();
    }

    private String generateText() {
        int size = generateTextLength();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(getRandomChar());
        }
        return sb.toString();
    }

    private int generateTextLength() {
        return new Random().nextInt(MAX_TEXT_LENGTH);
    }

    private char getRandomChar() {
        int charIndex = (int) (ALPHA_NUMERIC_STRING.length() * Math.random());
        return ALPHA_NUMERIC_STRING.charAt(charIndex);
    }

    public String getText() {
        return text;
    }

    @Testable
    class StringGeneratorTest {

        @Test
        void getRandomCharTest() {
            char chr = getRandomChar();
            int index = ALPHA_NUMERIC_STRING.indexOf(chr);
            assertTrue(index >= 0);
        }

        @Test
        void generateTextLengthTest() {
            int number = generateTextLength();
            assertTrue(number > 0);
            assertTrue(number < 180);
        }

        @Test
        void generateTextTest() {
            String text = generateText();
            assertTrue(text.length() < MAX_TEXT_LENGTH);
            assertFalse(text.isEmpty());
        }
    }
}
