package com.example.demo.controller;

import com.example.demo.services.BookServiceImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private BookServiceImpl service;

    @InjectMocks
    private BookController booksController;
    private List result;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(booksController).build();
        result = new ArrayList();
        result.add(1);
    }

    @Test
    public void libraryTest() throws Exception {
        //given
        when(service.findAll()).thenReturn(result);

        //when
        mockMvc.perform(get("/library"))

                //then
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").doesNotExist())
                .andExpect(jsonPath("$[0]").isNumber());
    }

    @Test
    public void searchTest() throws Exception {
        //given
        when(service.findByWord("jam")).thenReturn(result);

        //when
        mockMvc.perform(get("/search?word=jam"))

                //then
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").doesNotExist())
                .andExpect(jsonPath("$[0]").isNumber());
    }

    @Test
    public void filterTest() throws Exception {
        //given
        when(service.findByParams(any())).thenReturn(result);

        //when
        mockMvc.perform(get("/filter"))

                //then
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").doesNotExist())
                .andExpect(jsonPath("$[0]").isNumber());
    }
}