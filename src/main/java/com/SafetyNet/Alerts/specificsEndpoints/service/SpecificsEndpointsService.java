package com.SafetyNet.Alerts.specificsEndpoints.service;

import com.SafetyNet.Alerts.dtos.*;
import com.SafetyNet.Alerts.specificsEndpoints.service.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface SpecificsEndpointsService {

    public StationPersonsCoverage getAllCoveredPersons(long stationNumber) throws Exception;

    public PhonesCovered getAllCoveredPhones(long stationNumber) throws Exception;

    public Children getChildrenAndAdultsAtAddress(String address) throws Exception;

    public AddressInfo getAddressInfo(String address) throws Exception;

    public AddressesServiced getServicedAddressesInfo(Long stationNumber) throws Exception;

    public PersonsDetailsByNameAge getPersonsDetailsByNameAge(String firstName, String lastName) throws Exception;

    public AllMails getAllMails() throws Exception;
}
