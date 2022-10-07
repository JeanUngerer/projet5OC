package com.SafetyNet.Alerts.persons.service;

import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonsServiceImpl implements PersonsService {

    private final LiveDatas liveDatas;

    @Autowired
    public PersonsServiceImpl(LiveDatas liveDatas){
        this.liveDatas = liveDatas;
    }

    @Override
    public Persons getPersonById(long id)
    {
        return liveDatas.getPersonById(id);
    }

    @Override
    public List<Persons> getAllPersons() {
        return new ArrayList<Persons>(liveDatas.getAllPersons().values());
    }



    @Override
    public Persons deletePerson(Long id) {
        Persons removed = liveDatas.getPersonById(id);
        liveDatas.removePerson(id);
        return removed;
    }

    @Override
    public Persons createPerson(String firstName, String lastName, String address, String city, String phone, String email) {
        Long index = liveDatas.getPersonsIndex() + 1;
        Persons person = new Persons();
        person.setId(index);
        person.setAddress(address);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhone(phone);
        person.setCity(city);
        person.setEmail(email);
        liveDatas.setPersonsIndex(index);

        liveDatas.putPerson(index, person);
        return person;

    }

    @Override
    public Persons updatePerson(Long id, String firstName, String lastName, String address, String city, String phone, String email) {
        Persons updatedPerson = liveDatas.getPersonById(id);
        updatedPerson.setAddress(address);
        updatedPerson.setFirstName(firstName);
        updatedPerson.setLastName(lastName);
        updatedPerson.setPhone(phone);
        updatedPerson.setCity(city);
        updatedPerson.setEmail(email);

        liveDatas.putPerson(id, updatedPerson);
        return updatedPerson;
    }
}
