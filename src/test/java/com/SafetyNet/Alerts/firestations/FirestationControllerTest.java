package com.SafetyNet.Alerts.firestations;

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
public class FirestationControllerTest {


    @Autowired
    MockMvc mockMvc;



    @Test
    public void getAllFirestationsAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/firestations")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getFirestationByIdAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/firestations/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }


    @Test
    public void createFirestationAPI() throws Exception
    {
        String requestJson = "{ \"address\":\"1509 Culver St\", \"station\":\"4\" }";

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/firestations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("14")));


    }

    @Test
    public void updateFirestationAPI() throws Exception
    {
        String requestJson = "{ \"address\":\"1511 Culver St\", \"station\":\"3\" }";

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/firestations/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1511 Culver St")));
    }

    @Test
    public void deleteFirestationAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/firestations/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));
    }
}
