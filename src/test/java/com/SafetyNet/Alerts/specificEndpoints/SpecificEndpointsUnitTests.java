package com.SafetyNet.Alerts.specificEndpoints;

import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import com.SafetyNet.Alerts.persons.service.PersonsService;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import com.SafetyNet.Alerts.specificsEndpoints.service.SpecificsEndpointsServiceImpl;
import com.SafetyNet.Alerts.specificsEndpoints.service.domain.AddressInfo;
import com.SafetyNet.Alerts.specificsEndpoints.service.domain.Children;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SpecificEndpointsUnitTests {


    private static LiveDatas liveDatas;

    @Autowired
    SpecificsEndpointsServiceImpl specificsEndpointsService;


    @Autowired
    public SpecificEndpointsUnitTests(LiveDatas liveDatas){
        this.liveDatas = liveDatas;
    }

    @BeforeAll
    public static void prepareDatas(){

    }

    @Test
    public void getAddressInfoTest() throws Exception {
        String testAddress = "1509 Culver St";
        AddressInfo resultAddressInfo;
        try {
            resultAddressInfo = specificsEndpointsService.getAddressInfo(testAddress);
        } catch (Exception e) {
            throw e;
        }
        assertEquals(5, resultAddressInfo.getOccupants().size());
        assertEquals(testAddress, resultAddressInfo.getAddress());
    }

    @Test
    public void getChildrenAndAdultsAtAddressTest() throws Exception {
        String testAddress = "1509 Culver St";
        Children resultChildren;
        try {
            resultChildren = specificsEndpointsService.getChildrenAndAdultsAtAddress(testAddress);
        } catch (Exception e) {
            throw e;
        }
        System.out.println(resultChildren.toString());
        assertEquals(2, resultChildren.getChildren().size());
        assertEquals(3, resultChildren.getOtherHomeMembers().size());
    }
}
