package com.SafetyNet.Alerts.firestations;

import com.SafetyNet.Alerts.firestations.service.FirestationsService;
import com.SafetyNet.Alerts.firestations.service.domain.Firestations;
import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import com.SafetyNet.Alerts.persons.service.PersonsService;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FirestationServiceTest {


    private static LiveDatas liveDatas;

    @Autowired
    FirestationsService firestationsService;


    @Autowired
    public FirestationServiceTest(LiveDatas liveDatas){
        this.liveDatas = liveDatas;
    }

    @BeforeAll
    public static void prepareDatas(){

    }

    @Test
    public void getFirestationByIdTest() throws Exception {

        Firestations firestation = firestationsService.getFirestationById(1);

        System.out.println(liveDatas.toString());
        System.out.println(firestation.toString());

        assertEquals(1, firestation.getId());
        assertEquals("1509 Culver St", firestation.getAddress());
        assertEquals("3", firestation.getStation());
    }


    @Test
    public void getAllFirestationsTest() throws Exception{
        List<Firestations> allFirestations = firestationsService.getAllFirestations();

        Firestations firestation = allFirestations.get(0);

        assertEquals(13, allFirestations.size());
        assertEquals(1, firestation.getId());
        assertEquals("1509 Culver St", firestation.getAddress());
        assertEquals("3", firestation.getStation());
    }

}
