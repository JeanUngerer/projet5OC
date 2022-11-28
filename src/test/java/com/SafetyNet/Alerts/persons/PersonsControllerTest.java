package com.SafetyNet.Alerts.persons;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonsControllerTest {

    @Autowired
    MockMvc mockMvc;



    @Test
    public void getAllPersonsAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/persons")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getPersonByIdAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/persons/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }


    @Test
    public void createPersonAPI() throws Exception
    {
        String requestJson = "{ \"firstName\":\"mee\", \"lastName\":\"tooo\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"mtooo@email.com\" }";

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("24")));


    }

    @Test
    public void updatePersonAPI() throws Exception
    {
        String requestJson = "{\"id\":1, \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-7777\", \"email\":\"jaboyd@email.com\" }";

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/persons/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("841-874-7777")));
    }

    @Test
    public void deletePersonAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/persons/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));
    }
}
