package com.andrzej.pe.api;

import com.andrzej.pe.dao.CommentRequest;
import com.andrzej.pe.helpers.GrantedAuthorityHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CommentCDTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    GrantedAuthorityHelper grantedAuthorityHelper;

    @Before
    public void setup() {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void shouldCreateComment() throws Exception {
        CommentRequest commentRequest = new CommentRequest("autor", "cialo");
        this.mockMvc.perform(post("/api/comments")
                                .with(user("test")
                                .password("pass")
                                .authorities(grantedAuthorityHelper))
                .content(asJsonString(commentRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("autor"))
                .andExpect(jsonPath("$.body").value("cialo"));
    }

    private String asJsonString(CommentRequest commentRequest) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(commentRequest);
    }

    @Test
    public void shouldRemoveComments() throws Exception {
        this.mockMvc.perform(delete("/api/comments/1")
                             .with(user("test")
                             .password("pass")
                             .authorities(grantedAuthorityHelper)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
