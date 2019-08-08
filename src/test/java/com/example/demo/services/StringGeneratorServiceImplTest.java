package com.example.demo.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringGeneratorServiceImplTest {
    @Autowired
    private StringGeneratorServiceImpl service;

    @Test
    public void checkTextLength() {
        String text = service.getText();
        int textLength = text.length();
        assertTrue(textLength > 0);
        assertTrue(textLength < 180);
    }

    @Test
    public void getTextTest() {
        String text = service.getText();
        assertTrue(text.length() < 180);
        assertFalse(text.isEmpty());
    }
}