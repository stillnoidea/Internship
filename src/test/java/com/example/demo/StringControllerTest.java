package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(StringController.class)
public class StringControllerTest {

    @Test
    public void start() {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StringGenerator generator;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        when(generator.getText()).thenReturn("A");
        this.mockMvc.perform(get("/start")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("A")));

    }

    @Test
    public void test() {
    }
}