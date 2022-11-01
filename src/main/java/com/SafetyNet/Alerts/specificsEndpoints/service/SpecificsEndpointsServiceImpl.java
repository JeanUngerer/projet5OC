package com.SafetyNet.Alerts.specificsEndpoints.service;

import com.SafetyNet.Alerts.firestations.service.FirestationsService;
import com.SafetyNet.Alerts.firestations.service.domain.Firestations;
import com.SafetyNet.Alerts.medicalRecords.service.MedicalRecordsService;
import com.SafetyNet.Alerts.persons.service.PersonsService;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import com.SafetyNet.Alerts.specificsEndpoints.service.domain.*;
import com.SafetyNet.Alerts.utils.AddressesOccupants;
import com.SafetyNet.Alerts.utils.FullNameAge;
import com.SafetyNet.Alerts.utils.PersonDetails;
import com.SafetyNet.Alerts.utils.PersonsDetailsWithMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.SafetyNet.Alerts.constants.FixedPeriods.periodOfAdultYears;
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
        List<Firestations> firestationAddresses = firestationsService.getFirestationsByNumber(stationNumber);


        StationPersonsCoverage stationPersonsCoverage = new StationPersonsCoverage();

        long adultSouls;

        long childrenSouls;

        stationPersonsCoverage.setPersonsCovered(personsService.getAllPersons().stream().filter(f -> isCovered(f.getAddress(), firestationAddresses))
                .collect(Collectors.toList()));

        adultSouls = stationPersonsCoverage.getPersonsCovered().stream().filter(
                f -> isAdult(medicalRecordsService
                        .getMedicalRecordByName(f.getFirstName(), f.getLastName()).getBirthdate().toLocalDate())).count();

        childrenSouls = stationPersonsCoverage.getPersonsCovered().stream().filter(
                f -> !isAdult(medicalRecordsService
                        .getMedicalRecordByName(f.getFirstName(), f.getLastName()).getBirthdate().toLocalDate())).count();

        stationPersonsCoverage.setAdultsSouls(adultSouls);

        stationPersonsCoverage.setChildrenSouls(childrenSouls);

        return stationPersonsCoverage;
    }

    public PhonesCovered getAllCoveredPhones(long stationNumber) throws Exception {
        List<Firestations> firestationAddresses = firestationsService.getFirestationsByNumber(stationNumber);

        PhonesCovered phonesCovered = new PhonesCovered();

        phonesCovered.setPhonesNumbers(personsService.getAllPersons().stream().filter(f -> isCovered(f.getAddress(), firestationAddresses))
                .map(persons -> persons.getPhone())
                .collect(Collectors.toSet()).stream().toList());


        return phonesCovered;
    }

    public Children getChildrenAndAdultsAtAddress(String address) throws Exception {
        Children children = new Children();

        children.setChildren(getChildrenAtAddress(address));

        children.setOtherHomeMembers(getAdultsAtAddress(address));

        return children;
    }

    private List<FullNameAge> getChildrenAtAddress(String address) throws Exception {
        List<Persons> adultsAtAddress = personsService.getAllPersons().stream().filter(f -> f.getAddress().equals(address)).filter(
                        f ->    isAdult(medicalRecordsService
                                .getMedicalRecordByName(f.getFirstName(), f.getLastName()).getBirthdate().toLocalDate()))
                .collect(Collectors.toList());

        List<FullNameAge> adultsList = new ArrayList<>();

        adultsList.forEach(p -> adultsList.add(new FullNameAge(p.getFirstName(), p.getLastName(), computeAge(medicalRecordsService
                .getMedicalRecordByName(p.getFirstName(), p.getLastName()).getBirthdate().toLocalDate()))));

        return adultsList;
    }

    private List<FullNameAge> getAdultsAtAddress(String address) throws Exception {
        List<Persons> childrenAtAddress =  personsService.getAllPersons().stream().filter(f -> f.getAddress().equals(address)).filter(
                        f -> !isAdult(medicalRecordsService
                                .getMedicalRecordByName(f.getFirstName(), f.getLastName()).getBirthdate().toLocalDate()))
                .collect(Collectors.toList());

        List<FullNameAge> childrenList = new ArrayList<>();

        childrenAtAddress.forEach(p -> childrenList.add(new FullNameAge(p.getFirstName(), p.getLastName(), computeAge(medicalRecordsService
                .getMedicalRecordByName(p.getFirstName(), p.getLastName()).getBirthdate().toLocalDate()))));

        return childrenList;
    }

    public AddressInfo getAddressInfo(String address) throws Exception{

        AddressInfo addressInfo = new AddressInfo();

        addressInfo.setAddress(address);

        addressInfo.setOccupants(getAddressOccupants(address));

        addressInfo.setFirestationNumber(firestationsService.getFirestationByAddress(address).getStation());

        return addressInfo;
    }

    private List<PersonDetails> getAddressOccupants(String address) throws Exception {

        List<PersonDetails> personsDetails = new ArrayList<>();

        List<Persons> personsAtAddress = personsService.getAllPersons().stream().filter(f -> address.equals(f.getAddress())).collect(Collectors.toList());

        personsAtAddress.forEach(p -> personsDetails.add(getPersonDetails(p.getFirstName(), p.getLastName(), p.getPhone())));

        return personsDetails;
    }

    private PersonDetails getPersonDetails(String firstName, String lastName, String phone){
        PersonDetails personDetails = new PersonDetails(new FullNameAge(firstName, lastName, computeAge(medicalRecordsService
                .getMedicalRecordByName(firstName, lastName).getBirthdate().toLocalDate())),
                phone,
                medicalRecordsService
                        .getMedicalRecordByName(firstName, lastName).getMedications(),
                medicalRecordsService
                        .getMedicalRecordByName(firstName, lastName).getAllergies()
        );

        return personDetails;
    }



    public AddressesServiced getServicedAddressesInfo(Long stationNumber) throws Exception{
        List<String> addressesCovered = new ArrayList<>();
        firestationsService.getFirestationsByNumber(stationNumber).forEach(f -> addressesCovered.add(f.getAddress()));

        AddressesServiced addressesServiced = new AddressesServiced();

        List<AddressesOccupants> allAddressesOccupants = new ArrayList<>();

        addressesCovered.forEach(c -> {
            try {
                allAddressesOccupants.add(new AddressesOccupants(c, getAddressOccupants(c)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        addressesServiced.setHomesCovered(allAddressesOccupants);
        addressesServiced.setStationNumber(stationNumber.toString());

        return addressesServiced;
    }

    public PersonsDetailsByNameAge getPersonsDetailsByNameAge(String firstName, String lastName) throws Exception{

        List<PersonsDetailsWithMail> allCorresponding = new ArrayList<>();

        List<Persons> personsWithName = personsService.getAllPersons().stream()
                .filter(f -> f.getFirstName().equals(firstName))
                .filter(f -> f.getLastName().equals(lastName))
                .collect(Collectors.toList());

        personsWithName.forEach(p -> allCorresponding.add(getPersonDetailsWithMaill(p.getFirstName(), p.getLastName(), p.getPhone(), p.getEmail())));

        return new PersonsDetailsByNameAge(allCorresponding);
    }

    private PersonsDetailsWithMail getPersonDetailsWithMaill(String firstName, String lastName, String phone, String mail){
        PersonsDetailsWithMail personsDetailsWithMail = new PersonsDetailsWithMail(new FullNameAge(firstName, lastName, computeAge(medicalRecordsService
                .getMedicalRecordByName(firstName, lastName).getBirthdate().toLocalDate())),
                phone,
                mail,
                medicalRecordsService
                        .getMedicalRecordByName(firstName, lastName).getMedications(),
                medicalRecordsService
                        .getMedicalRecordByName(firstName, lastName).getAllergies()
        );

        return personsDetailsWithMail;
    }

    public AllMails getAllMails() throws Exception{

        List<String> mailList = personsService.getAllPersons().stream()
                .map(Persons::getEmail)
                .collect(Collectors.toList());
        return new AllMails(mailList);
    }




    private boolean isCovered (String personAddress, List<Firestations> allFirestationsAddresses){
        return allFirestationsAddresses.stream().anyMatch(firestations -> firestations.getAddress().equals(personAddress));
    }

    private boolean isAdult (LocalDate birthdate) {
        return Period.between(birthdate , ZonedDateTime.now().toLocalDate())
                .getYears() >= periodOfAdultYears.getYears();
    }

    private int computeAge (LocalDate birthdate){
        return Period.between(birthdate , ZonedDateTime.now().toLocalDate())
                .getYears();
    }

}
