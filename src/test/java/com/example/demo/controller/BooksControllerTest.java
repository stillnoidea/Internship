package com.example.demo.controller;

import com.example.demo.services.BooksServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = BooksController.class)
public class BooksControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private BooksServiceImpl service;
    @InjectMocks
    private BooksController booksController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(booksController).build();
    }

    @Test
    public void start() throws Exception {
        List result= new ArrayList();
        result.add(1);
        when(service.findAll()).thenReturn(result);
        mockMvc.perform(get("/library"))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(content().string(containsString("1")));
    }
}