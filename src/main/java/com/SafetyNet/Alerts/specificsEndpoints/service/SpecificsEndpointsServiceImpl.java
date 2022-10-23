package com.SafetyNet.Alerts.specificsEndpoints.service;

import com.SafetyNet.Alerts.firestations.service.FirestationsService;
import com.SafetyNet.Alerts.firestations.service.domain.Firestations;
import com.SafetyNet.Alerts.medicalRecords.service.MedicalRecordsService;
import com.SafetyNet.Alerts.persons.service.PersonsService;
import com.SafetyNet.Alerts.specificsEndpoints.service.domain.StationPersonsCoverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import static java.time.Duration.between;

@Service
public class SpecificsEndpointsServiceImpl implements SpecificsEndpointsService {


    @Autowired
    PersonsService personsService;

    @Autowired
    FirestationsService firestationsService;
    @Autowired
    MedicalRecordsService medicalRecordsService;

    public StationPersonsCoverage getAllCoveredPersons(long stationNumber) throws Exception {
        Firestations firestations = firestationsService.getFirestationById(stationNumber);
        StationPersonsCoverage stationPersonsCoverage = new StationPersonsCoverage();

        Period periodOfAdultYears = Period.ofYears(18);

        long adultSouls;

        long childrenSouls;

        stationPersonsCoverage.setPersonsCovered(personsService.getAllPersons().stream().filter(f -> firestations.getAddress().equals(f.getAddress())).collect(Collectors.toList()));

        adultSouls = stationPersonsCoverage.getPersonsCovered().stream().filter(
                f ->
                    Period.between(medicalRecordsService
                            .getMedicalRecordByName(f.getFirstName(), f.getLastName()).getBirthdate().toLocalDate() , ZonedDateTime.now().toLocalDate())
                            .getDays() >= periodOfAdultYears.getDays()).count();

        childrenSouls = stationPersonsCoverage.getPersonsCovered().stream().filter(
                f ->
                        Period.between(medicalRecordsService
                                        .getMedicalRecordByName(f.getFirstName(), f.getLastName()).getBirthdate().toLocalDate() , ZonedDateTime.now().toLocalDate())
                                .getDays() < periodOfAdultYears.getDays()).count();

        stationPersonsCoverage.setAdultsSouls(adultSouls);

        stationPersonsCoverage.setChildrenSouls(childrenSouls);

        return stationPersonsCoverage;
    }

}
