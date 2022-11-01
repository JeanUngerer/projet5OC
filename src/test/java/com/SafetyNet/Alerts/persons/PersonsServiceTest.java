package com.SafetyNet.Alerts.persons;

import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import com.SafetyNet.Alerts.persons.service.PersonsService;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonsServiceTest {

    private static LiveDatas liveDatas;

    @Autowired
    PersonsService personsService;


    @Autowired
    public PersonsServiceTest(LiveDatas liveDatas){
        this.liveDatas = liveDatas;
    }

    @BeforeAll
    public static void prepareDatas(){

    }

    @Test
    public void getPersonByIdTest() throws Exception {

        Persons person = personsService.getPersonById(1);

        System.out.println(liveDatas.toString());
        System.out.println(person.toString());

        assertEquals(1, person.getId());
        assertEquals("Culver", person.getCity());
        assertEquals("97451", person.getZip());
        assertEquals("841-874-6512", person.getPhone());
        assertEquals("John", person.getFirstName());
        assertEquals("Boyd", person.getLastName());
        assertEquals("1509 Culver St", person.getAddress());
        assertEquals("jaboyd@email.com", person.getEmail());
    }
}