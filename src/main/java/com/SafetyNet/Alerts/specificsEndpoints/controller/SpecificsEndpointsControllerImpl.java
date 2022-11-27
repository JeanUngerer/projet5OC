package com.SafetyNet.Alerts.specificsEndpoints.controller;

import com.SafetyNet.Alerts.dtos.*;
import com.SafetyNet.Alerts.specificsEndpoints.service.SpecificsEndpointsService;
import com.SafetyNet.Alerts.specificsEndpoints.service.domain.AddressInfo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("${spring.data.rest.base-path}")
public class SpecificsEndpointsControllerImpl {

    @Autowired
    SpecificsEndpointsService specificsEndpointsService;
    private static final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/firestation")
    public ResponseEntity<StationPersonsCoverageDTO> getAllCoveredPersons(@RequestParam Long stationNumber) throws Exception {

        StationPersonsCoverageDTO dto = modelMapper.map(specificsEndpointsService.getAllCoveredPersons(stationNumber), StationPersonsCoverageDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<PhonesCoveredDTO> getAllCoveredPhones(@RequestParam Long firestation) throws Exception{
        PhonesCoveredDTO dto = modelMapper
                .map(specificsEndpointsService.getAllCoveredPhones(firestation), PhonesCoveredDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/childAlert")
    public ResponseEntity<ChildrenDTO> getChildrenAtAddress(@RequestParam String address) throws Exception {
        address = address.replace("+", " ");
        ChildrenDTO dto = modelMapper.map(specificsEndpointsService.getChildrenAndAdultsAtAddress(address), ChildrenDTO.class);
        log.info("USED : /childAlert" + dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/fire")
    ResponseEntity<AddressInfoDTO> getAddressInfo(@RequestParam String address) throws Exception {
        address = address.replace("+", " ");
        AddressInfoDTO dto = modelMapper.map(specificsEndpointsService.getAddressInfo(address), AddressInfoDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/flood/stations")
    ResponseEntity<AddressesServicedDTO> getServicedAddressesInfo(@RequestParam Long stations) throws Exception{
        AddressesServicedDTO dto = modelMapper.map(specificsEndpointsService.getServicedAddressesInfo(stations), AddressesServicedDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/personInfo")
    ResponseEntity<PersonsDetailsByNameAgeDTO> getPersonsDetailsByNameAge(@RequestParam String firstName, @RequestParam String lastName) throws Exception{
        firstName = firstName.replace("+", " ");
        lastName = lastName.replace("+", " ");
        PersonsDetailsByNameAgeDTO dto = modelMapper.map(specificsEndpointsService.getPersonsDetailsByNameAge(firstName, lastName), PersonsDetailsByNameAgeDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/communityEmail")
    ResponseEntity<AllMailsDTO> getAllMails() throws Exception{
        AllMailsDTO dto = modelMapper.map(specificsEndpointsService.getAllMails(), AllMailsDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
