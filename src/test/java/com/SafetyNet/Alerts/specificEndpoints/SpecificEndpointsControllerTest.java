package com.SafetyNet.Alerts.specificEndpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SpecificEndpointsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllCoveredPersonsAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/firestation?stationNumber=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getAllCoveredPhonesAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/phoneAlert?firestation=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getChildrenAtAddressAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/childAlert?address=1509+Culver+St")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getAddressInfoAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/fire?address=1509+Culver+St")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getServicedAddressesInfoAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/flood/stations?stations=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getPersonsDetailsByNameAgeAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/personInfo?firstName=John&lastName=Boyd")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getAllMailsAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/communityEmail")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}
