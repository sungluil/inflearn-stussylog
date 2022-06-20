package com.example.blog2022.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("post 테스트")
    void test1() throws Exception {
        mockMvc.perform(post("/post1"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world"));
    }

    @Test
    @DisplayName("post 테스트")
    void test2() throws Exception {
        mockMvc.perform(post("/post2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"stussy\", \"age\": \"22\"}")
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("post 테스트")
    void test3() throws Exception {
        mockMvc.perform(post("/post2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"\", \"age\": \"22\"}")
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorCode").value("400"))
                .andExpect(jsonPath("$.errorMessage").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.exceptionDtoSet.*.defaultMessage")
                        .value("must not be blank"));
    }
}