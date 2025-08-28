package com.example.restservice;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RandomControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void randomShouldReturnNumberBetween1And10() throws Exception {
        this.mockMvc.perform(get("/random")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(is(anyOf(
                        equalTo("1"), equalTo("2"), equalTo("3"), equalTo("4"), equalTo("5"),
                        equalTo("6"), equalTo("7"), equalTo("8"), equalTo("9"), equalTo("10")
                ))));
    }

    // run multiple times to reduce flakiness by sampling other values
    @RepeatedTest(5)
    public void repeatedRandomsShouldBeValid() throws Exception {
        this.mockMvc.perform(get("/random")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(is(anyOf(
                        equalTo("1"), equalTo("2"), equalTo("3"), equalTo("4"), equalTo("5"),
                        equalTo("6"), equalTo("7"), equalTo("8"), equalTo("9"), equalTo("10")
                ))));
    }
}
