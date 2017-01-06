package com.andrzej.pe.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnComments() throws Exception {
        this.mockMvc.perform(get("/api/comments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[1].author").value("Autor 2"))
                .andExpect(jsonPath("$.[2].body").value("Komentarz numer 3"));
    }

    @Test
    public void shouldRemoveComments() throws Exception {
        this.mockMvc.perform(delete("/api/comments"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
