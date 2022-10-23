package com.SafetyNet.Alerts.specificsEndpoints.service;

import com.SafetyNet.Alerts.specificsEndpoints.service.domain.StationPersonsCoverage;

public interface SpecificsEndpointsService {

    public StationPersonsCoverage getAllCoveredPersons(long stationNumber) throws Exception;
}
