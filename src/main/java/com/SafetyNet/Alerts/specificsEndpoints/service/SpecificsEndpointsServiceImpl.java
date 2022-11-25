package com.SafetyNet.Alerts.specificsEndpoints.service;

import com.SafetyNet.Alerts.constants.SafetyNetsErrorMessages;
import com.SafetyNet.Alerts.errorsType.ExceptionHandler;
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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SpecificsEndpointsServiceImpl implements SpecificsEndpointsService {


    @Autowired
    PersonsService personsService;

    @Autowired
    FirestationsService firestationsService;
    @Autowired
    MedicalRecordsService medicalRecordsService;

    public StationPersonsCoverage getAllCoveredPersons(long stationNumber) throws Exception {



        try {
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
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    public PhonesCovered getAllCoveredPhones(long stationNumber) throws Exception {


        try {
            List<Firestations> firestationAddresses = firestationsService.getFirestationsByNumber(stationNumber);

            PhonesCovered phonesCovered = new PhonesCovered();

            phonesCovered.setPhonesNumbers(personsService.getAllPersons().stream().filter(f -> isCovered(f.getAddress(), firestationAddresses))
                    .map(persons -> persons.getPhone())
                    .collect(Collectors.toSet()).stream().toList());


            return phonesCovered;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    public Children getChildrenAndAdultsAtAddress(String address) throws Exception {


        try {
            Children children = new Children();

            children.setChildren(getChildrenAtAddress(address));

            children.setOtherHomeMembers(getAdultsAtAddress(address));

            return children;
        } catch (Exception e){
            log.error("error computing Children class instance");
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    private List<FullNameAge> getAdultsAtAddress(String address) throws Exception {


        try {
            List<Persons> adultsAtAddress = personsService.getAllPersons().stream().filter(f -> f.getAddress().equals(address)).filter(
                            f ->    isAdult(medicalRecordsService
                                    .getMedicalRecordByName(f.getFirstName(), f.getLastName()).getBirthdate().toLocalDate()))
                    .collect(Collectors.toList());

            List<FullNameAge> adultsList = new ArrayList<>();

            adultsAtAddress.forEach(p -> adultsList.add(new FullNameAge(p.getFirstName(), p.getLastName(), computeAge(medicalRecordsService
                    .getMedicalRecordByName(p.getFirstName(), p.getLastName()).getBirthdate().toLocalDate()))));

            return adultsList;
        } catch (Exception e){
            log.error("error computing adults at address");
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    private List<FullNameAge> getChildrenAtAddress(String address) throws Exception {


        try {

            log.info("Before all childAlert in service");
            List<Persons> childrenAtAddress =  personsService.getAllPersons().stream().filter(f -> f.getAddress().equals(address)).filter(
                            f -> !isAdult(medicalRecordsService
                                    .getMedicalRecordByName(f.getFirstName(), f.getLastName()).getBirthdate().toLocalDate()))
                    .collect(Collectors.toList());
            log.info("ListPersons PASSED");

            List<FullNameAge> childrenList = new ArrayList<>();

            log.info("new arrayList PASSED");

            childrenAtAddress.forEach(p -> childrenList.add(new FullNameAge(p.getFirstName(), p.getLastName(), computeAge(medicalRecordsService
                    .getMedicalRecordByName(p.getFirstName(), p.getLastName()).getBirthdate().toLocalDate()))));

            log.debug("list add PASSED");
            return childrenList;
        } catch (Exception e){
            log.error("error computing children at address");
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    public AddressInfo getAddressInfo(String address) throws Exception{



        try {
            AddressInfo addressInfo = new AddressInfo();

            addressInfo.setAddress(address);

            addressInfo.setOccupants(getAddressOccupants(address));

            addressInfo.setFirestationNumber(firestationsService.getFirestationByAddress(address).getStation());

            return addressInfo;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND + " // " + address, e);
        }
    }

    private List<PersonDetails> getAddressOccupants(String address) throws Exception {



        try {
            List<PersonDetails> personsDetails = new ArrayList<>();

            List<Persons> personsAtAddress = personsService.getAllPersons().stream().filter(f -> address.equals(f.getAddress())).collect(Collectors.toList());

            personsAtAddress.forEach(p -> personsDetails.add(getPersonDetails(p.getFirstName(), p.getLastName(), p.getPhone())));

            return personsDetails;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    private PersonDetails getPersonDetails(String firstName, String lastName, String phone){


        try {
            PersonDetails personDetails = new PersonDetails(new FullNameAge(firstName, lastName, computeAge(medicalRecordsService
                    .getMedicalRecordByName(firstName, lastName).getBirthdate().toLocalDate())),
                    phone,
                    medicalRecordsService
                            .getMedicalRecordByName(firstName, lastName).getMedications(),
                    medicalRecordsService
                            .getMedicalRecordByName(firstName, lastName).getAllergies()
            );

            return personDetails;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }



    public AddressesServiced getServicedAddressesInfo(Long stationNumber) throws Exception{


        try {
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
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    public PersonsDetailsByNameAge getPersonsDetailsByNameAge(String firstName, String lastName) throws Exception{



        try {
            List<PersonsDetailsWithMail> allCorresponding = new ArrayList<>();

            List<Persons> personsWithName = personsService.getAllPersons().stream()
                    .filter(f -> f.getFirstName().equals(firstName))
                    .filter(f -> f.getLastName().equals(lastName))
                    .collect(Collectors.toList());

            personsWithName.forEach(p -> allCorresponding.add(getPersonDetailsWithMaill(p.getFirstName(), p.getLastName(), p.getPhone(), p.getEmail())));

            return new PersonsDetailsByNameAge(allCorresponding);
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    private PersonsDetailsWithMail getPersonDetailsWithMaill(String firstName, String lastName, String phone, String mail){


        try {
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
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    public AllMails getAllMails() throws Exception{



        try {
            List<String> mailList = personsService.getAllPersons().stream()
                    .map(Persons::getEmail)
                    .collect(Collectors.toList());
            return new AllMails(mailList);
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }




    private boolean isCovered (String personAddress, List<Firestations> allFirestationsAddresses){
        return allFirestationsAddresses.stream().anyMatch(firestations -> firestations.getAddress().equals(personAddress));
    }

    private boolean isAdult (LocalDate birthdate) {
        try {
            log.info("IS ADULT");
            return Period.between(birthdate, ZonedDateTime.now().toLocalDate())
                    .getYears() >= periodOfAdultYears.getYears();
        } catch (Exception e){
            log.error("error computing is adults");
            throw new ExceptionHandler("Cannot compute isAdult //", e);
        }
    }

    private int computeAge (LocalDate birthdate){
        try {
            return Period.between(birthdate, ZonedDateTime.now().toLocalDate())
                    .getYears();
        } catch (Exception e){
            log.error("error computing age");
            throw new ExceptionHandler("Cannot compute age from : " + birthdate.toString() + "//", e);
        }
    }

}
