package com.SafetyNet.Alerts.persons.service;


import com.SafetyNet.Alerts.persons.service.domain.Persons;

import java.time.ZonedDateTime;
import java.util.List;

public interface PersonsService {
    public Persons getPersonById(long id) throws Exception;

    public List<Persons> getAllPersons() throws Exception;

    public Persons createPerson(String firstName, String lastName, String address, String city, String phone, String email);

    public Persons updatePerson(Long id, String firstName, String lastName, String address, String city, String phone, String email);

    public Persons deletePerson(Long id);
}
