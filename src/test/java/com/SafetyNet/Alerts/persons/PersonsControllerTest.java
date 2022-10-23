package com.SafetyNet.Alerts.persons;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonsControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void mockMVCTest() throws Exception {
        //MockMvc mockMvc = webAppContextSetup(wac).build();

        MvcResult result = mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse());
        assertTrue(true);
    }
}
