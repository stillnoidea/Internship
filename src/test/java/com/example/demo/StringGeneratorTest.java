package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringGeneratorTest extends StringGenerator{

    @Test
    public void getRandomCharTest() {
        char chr = getRandomChar();
        int index = ALPHA_NUMERIC_STRING.indexOf(chr);
        assertTrue(index >= 0);
    }

    @Test
    public void generateTextLengthTest() {
        int number = generateTextLength();
        assertTrue(number > 0);
        assertTrue(number < 180);
    }

    @Test
    public void generateTextTest() {
        String text = generateText();
        assertTrue(text.length() < MAX_TEXT_LENGTH);
        assertFalse(text.isEmpty());
    }
}