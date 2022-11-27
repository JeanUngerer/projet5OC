package com.SafetyNet.Alerts.specificsEndpoints.controller;

import com.SafetyNet.Alerts.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface SpecificsEndpointsController {


    ResponseEntity<StationPersonsCoverageDTO> getAllCoveredPersons(@RequestParam Long sationNumber) throws Exception;

    ResponseEntity<PhonesCoveredDTO> getAllCoveredPhones(@RequestParam Long sationNumber) throws Exception;

    ResponseEntity<ChildrenDTO> getChildrenAtAddress(@RequestParam String address) throws Exception;

    ResponseEntity<AddressInfoDTO> getAddressInfo(@RequestParam String address) throws Exception;

    ResponseEntity<AddressesServicedDTO> getServicedAddressesInfo(@RequestParam String stationNumber) throws Exception;

    ResponseEntity<PersonsDetailsByNameAgeDTO> getPersonsDetailsByNameAge(@RequestParam String firstName, @RequestParam String lastName) throws Exception;

    ResponseEntity<AllMailsDTO> getAllMails() throws Exception;


}
