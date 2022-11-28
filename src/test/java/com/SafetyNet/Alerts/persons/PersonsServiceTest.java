package com.SafetyNet.Alerts.persons;

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
    }

    @Test
    public void getPersonByNameTest() throws Exception{
        Persons person = personsService.getPersonByName("John", "Boyd");

        assertEquals(1, person.getId());
        assertEquals("Culver", person.getCity());
        assertEquals("97451", person.getZip());
        assertEquals("841-874-6512", person.getPhone());
        assertEquals("John", person.getFirstName());
        assertEquals("Boyd", person.getLastName());
        assertEquals("1509 Culver St", person.getAddress());
    }

    @Test
    public void getAllPersonsTest() throws Exception{
        List<Persons> allPersons = personsService.getAllPersons();

        Persons person = allPersons.get(0);

        assertEquals(23, allPersons.size());
        assertEquals(1, person.getId());
        assertEquals("Culver", person.getCity());
        assertEquals("97451", person.getZip());
        assertEquals("841-874-6512", person.getPhone());
        assertEquals("John", person.getFirstName());
        assertEquals("Boyd", person.getLastName());


    }

    @Test
    public void updatePersonTest() throws  Exception{
        Persons personToUpdate = new Persons();
        personToUpdate.setId(Long.valueOf(1));
        personToUpdate.setCity("Culver");
        personToUpdate.setZip("97451");
        personToUpdate.setPhone("841-874-6512");
        personToUpdate.setFirstName("John");
        personToUpdate.setLastName("Boyd");
        personToUpdate.setAddress("1509 Culver St");
        personToUpdate.setEmail("johnLEEboyd@email.com");

        Persons person = personsService.updatePerson(personToUpdate);

        Persons personUpdated = personsService.getPersonByName("John", "Boyd");

        assertEquals(1, person.getId());
        assertEquals("Culver", person.getCity());
        assertEquals("97451", person.getZip());
        assertEquals("841-874-6512", person.getPhone());
        assertEquals("John", person.getFirstName());
        assertEquals("Boyd", person.getLastName());
        assertEquals("1509 Culver St", person.getAddress());
        assertEquals("johnLEEboyd@email.com", person.getEmail());

        assertEquals(1, personUpdated.getId());
        assertEquals("Culver", personUpdated.getCity());
        assertEquals("97451", personUpdated.getZip());
        assertEquals("841-874-6512", personUpdated.getPhone());
        assertEquals("John", personUpdated.getFirstName());
        assertEquals("Boyd", personUpdated.getLastName());
        assertEquals("1509 Culver St", personUpdated.getAddress());
        assertEquals("johnLEEboyd@email.com", personUpdated.getEmail());


    }
}