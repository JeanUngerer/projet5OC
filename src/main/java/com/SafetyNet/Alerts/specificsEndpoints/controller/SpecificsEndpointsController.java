package com.SafetyNet.Alerts.specificsEndpoints.controller;

import com.SafetyNet.Alerts.dtos.PersonsDTO;
import com.SafetyNet.Alerts.dtos.StationPersonsCoverageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SpecificsEndpointsController {


    ResponseEntity<StationPersonsCoverageDTO> getAllCoveredPersons(@PathVariable Long sationNumber) throws Exception;

}
