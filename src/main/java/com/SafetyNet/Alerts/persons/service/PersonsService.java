package com.SafetyNet.Alerts.persons.service;


import com.SafetyNet.Alerts.persons.service.domain.Persons;

import java.time.ZonedDateTime;
import java.util.List;

public interface PersonsService {
    public Persons getPersonById(long id) throws Exception;

    public Persons getPersonByName(String firstName, String lastName) throws Exception;

    public List<Persons> getAllPersons() throws Exception;

    public Persons createPerson(Persons person);

    public Persons updatePerson(Persons updatePerson);

    public Persons deletePerson(Long id);
}
