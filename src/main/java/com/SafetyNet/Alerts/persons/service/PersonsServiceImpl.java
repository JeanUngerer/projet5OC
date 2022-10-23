package com.SafetyNet.Alerts.persons.service;

import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Persons getPersonByName(String firstName, String lastName)
    {
        List<Persons> allPersons = new ArrayList<Persons>(liveDatas.getAllPersons().values());

        return getNameFilteredList(allPersons, firstName, lastName);
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
    public Persons createPerson(Persons person) {
        Long index = liveDatas.getPersonsIndex() + 1;
        liveDatas.setPersonsIndex(index);
        person.setId(index);
        liveDatas.putPerson(index, person);
        return person;

    }

    @Override
    public Persons updatePerson(Persons updatePerson) {
        Persons updatedPerson = liveDatas.getPersonById(updatePerson.getId());
        liveDatas.putPerson(updatePerson.getId(), updatedPerson);
        return updatedPerson;
    }


    private Persons getNameFilteredList(List<Persons> entryList, String firstName, String lastName) {
        return entryList.stream().filter(f -> firstName.compareTo(f.getFirstName()) > 0).filter(f -> lastName.compareTo(f.getLastName()) > 0).collect(Collectors.toList()).get(0);
    }


}
