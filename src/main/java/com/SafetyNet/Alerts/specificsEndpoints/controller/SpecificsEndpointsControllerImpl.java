package com.SafetyNet.Alerts.specificsEndpoints.controller;

import com.SafetyNet.Alerts.dtos.StationPersonsCoverageDTO;
import com.SafetyNet.Alerts.specificsEndpoints.service.SpecificsEndpointsService;
import com.SafetyNet.Alerts.specificsEndpoints.service.SpecificsEndpointsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.stream.Stream;
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
    ModelMapper modelMapper;

    @GetMapping("/firestation")
    public ResponseEntity<StationPersonsCoverageDTO> getAllCoveredPersons(@RequestParam Long stationNumber) throws Exception {
        System.out.println(stationNumber);

        StationPersonsCoverageDTO dto = modelMapper.map(specificsEndpointsService.getAllCoveredPersons(stationNumber), StationPersonsCoverageDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
