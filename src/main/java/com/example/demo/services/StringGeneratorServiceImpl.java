package com.example.demo.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StringGeneratorServiceImpl implements StringGeneratorService {

    private final static int MAX_TEXT_LENGTH = 180;

    private int generateTextLength() {
        return new Random().nextInt(MAX_TEXT_LENGTH) + 5;
    }

    public String getText() {
        return RandomStringUtils.randomAlphabetic(generateTextLength());
    }
}