package com.SafetyNet.Alerts.specificEndpoints;

import com.SafetyNet.Alerts.constants.SafetyNetsErrorMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.net.URLDecoder;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders
                        .get("/firestation?stationNumber=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("boyd")))
                .andReturn();

    }

 @Test
    public void getAllCoveredPhonesAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/phoneAlert?firestation=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("841-874-7784")))
                .andReturn();
    }

    //@Test
    public void getChildrenAtAddressAPI() throws Exception
    {
        String address = "1509 Culver St";
        String endpointUrl = "childAlert";
        String url = "/{enpointUrl}?address={address}";
        URI expanded = new UriTemplate(url).expand(endpointUrl, address); // this is what RestTemplate uses
        url = URLDecoder.decode(expanded.toString(), "UTF-8"); // java.net class


        mockMvc.perform( MockMvcRequestBuilders
                        .get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("children")))
                .andReturn();
    }

    //@Test
    public void getAddressInfoAPI() throws Exception
    {

        String address = "1509 Culver St";
        String endpointUrl = "fire";
        String url = "/{enpointUrl}?address={address}";
        URI expanded = new UriTemplate(url).expand(endpointUrl, address); // this is what RestTemplate uses
        url = URLDecoder.decode(expanded.toString(), "UTF-8"); // java.net class
        mockMvc.perform( MockMvcRequestBuilders
                        .get(url)

                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              //  .andExpect(content().string(containsString("Boyd")))
                .andReturn();
    }

    @Test
    public void getServicedAddressesInfoAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/flood/stations?stations=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("homesCovered")))
                .andReturn();
    }

    @Test
    public void getPersonsDetailsByNameAgeAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/personInfo")
                        .param("firstName", "John")
                        .param("lastName", "Boyd")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Boyd")))
                .andReturn();
    }

    @Test
    public void getAllMailsAPI() throws Exception
    {
        MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders
                        .get("/communityEmail")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("email")))
                .andReturn();
    }
}
