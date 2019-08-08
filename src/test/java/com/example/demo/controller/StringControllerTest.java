package com.example.demo.controller;

import com.example.demo.services.StringGeneratorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = StringController.class)
public class StringControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private StringGeneratorServiceImpl service;
    @InjectMocks
    private StringController stringController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(stringController).build();
    }

    @Test
    public void startTest() throws Exception {
        when(service.getText()).thenReturn("A12nka");
        mockMvc.perform(get("/start"))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(content().string(containsString("A12nka")));
    }
}