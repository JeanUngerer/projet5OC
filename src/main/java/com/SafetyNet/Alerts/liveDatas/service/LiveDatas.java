package com.SafetyNet.Alerts.liveDatas.service;


import java.util.HashMap;
import java.util.Map;

import com.SafetyNet.Alerts.firestations.service.domain.Firestations;

import com.SafetyNet.Alerts.persons.service.domain.Persons;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;


@Getter
@Setter
@ToString
@Service
@Slf4j
public class LiveDatas {

    private Map<Long, MedicalRecords> medicalRecords;
    private Long medicalRecordsIndex;

    private Map<Long, Persons> persons;

    private Long personsIndex;

    private Map<Long, Firestations> firestations;

    private Long firestationsIndex;


    public LiveDatas() {
        this.medicalRecordsIndex = 0L;
        this.personsIndex = 0L;
        this.firestationsIndex = 0L;
        this.persons = new HashMap<>();
        this.firestations = new HashMap<>();
        this.medicalRecords = new HashMap<>();
    }

    public Map<Long, MedicalRecords> getAllMedicalRecords() {
        return this.medicalRecords;
    }

    public MedicalRecords getMedicalRecordById(Long id){
        return this.medicalRecords.get(id);
    }

    public void putMedicalRecord(Long id, MedicalRecords record){
        this.medicalRecords.put(id, record);
    }

    public void removeMedicalRecord(Long id){
        this.medicalRecords.remove(id);
    }



    public Long getMedicalRecordsIndex(){
        return this.medicalRecordsIndex;
    }

    public void setMedicalRecordsIndex(Long index){
        this.medicalRecordsIndex = index;
    }


    public Map<Long, Persons> getAllPersons() {
        return this.persons;
    }

    public Persons getPersonById(Long id){
        return this.persons.get(id);
    }

    public void putPerson(Long id, Persons record){
        this.persons.put(id, record);
    }

    public void removePerson(Long id){
        this.persons.remove(id);
    }



    public Long getPersonsIndex(){
        return this.personsIndex;
    }

    public void setPersonsIndex(Long index){
        this.personsIndex = index;
    }


    public Map<Long, Firestations> getAllFirestations() {
        return this.firestations;
    }

    public Firestations getFirestationById(Long id){
        return this.firestations.get(id);
    }

    public Firestations getFirestationByAddress(String address){
        final Firestations[] firestation = new Firestations[1];
        this.firestations.values().forEach(f -> {if (f.getAddress().equals(address)) {
        firestation[0] = f;}
        });

        return  firestation[0];
    }

    public void putFirestation(Long id, Firestations record){
        this.firestations.put(id, record);
    }

    public void removeFirestation(Long id){
        this.firestations.remove(id);
    }



    public Long getFirestationsIndex(){
        return this.firestationsIndex;
    }

    public void setFirestationsIndex(Long index){
        this.firestationsIndex = index;
    }
}
