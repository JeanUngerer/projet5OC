package com.SafetyNet.Alerts.persons.service;

import com.SafetyNet.Alerts.constants.SafetyNetsErrorMessages;
import com.SafetyNet.Alerts.errorsType.ExceptionHandler;
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

        try {
            return liveDatas.getPersonById(id);
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    public Persons getPersonByName(String firstName, String lastName)
    {
        List<Persons> allPersons = new ArrayList<Persons>(liveDatas.getAllPersons().values());


        try {
            return getNameFilteredPerson(allPersons, firstName, lastName);
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    @Override
    public List<Persons> getAllPersons() {

        try {
            return new ArrayList<Persons>(liveDatas.getAllPersons().values());
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    public List<Persons> getAllPersonsAtAddress(String address) throws Exception{


        try {
            return null;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }



    @Override
    public Persons deletePerson(Long id) {

        try {
            Persons removed = liveDatas.getPersonById(id);
            liveDatas.removePerson(id);
            return removed;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NO_DELETE, e);
        }
    }

    @Override
    public Persons createPerson(Persons person) {

        try {
            Long index = liveDatas.getPersonsIndex() + 1;
            liveDatas.setPersonsIndex(index);
            person.setId(index);
            liveDatas.putPerson(index, person);
            return person;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NO_CREATION, e);
        }

    }

    @Override
    public Persons updatePerson(Persons updatePerson) {

        try {
            Persons updatedPerson = liveDatas.getPersonById(updatePerson.getId());
            liveDatas.putPerson(updatePerson.getId(), updatedPerson);
            return updatedPerson;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NO_UPDATE, e);
        }
    }


    private Persons getNameFilteredPerson(List<Persons> entryList, String firstName, String lastName) {
        List<Persons> personsFound = entryList.stream().filter(f -> firstName.equals(f.getFirstName())).filter(f -> lastName.equals(f.getLastName())).collect(Collectors.toList());
        if (personsFound.size() > 0) {
            return personsFound.get(0);
        }
        return null;
    }


}
