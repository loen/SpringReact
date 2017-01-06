package com.andrzej.pe.api;

import com.andrzej.pe.dao.CommentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CommentCDTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateComment() throws Exception {
        CommentRequest commentRequest = new CommentRequest("autor", "cialo");
        this.mockMvc.perform(post("/api/comments")
                .content(asJsonString(commentRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String asJsonString(CommentRequest commentRequest) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(commentRequest);
    }

    @Test
    public void shouldRemoveComments() throws Exception {
        this.mockMvc.perform(delete("/api/comments/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
