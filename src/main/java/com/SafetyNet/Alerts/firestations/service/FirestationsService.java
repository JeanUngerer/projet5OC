package com.SafetyNet.Alerts.firestations.service;

import com.SafetyNet.Alerts.dtos.FirestationsDTO;
import com.SafetyNet.Alerts.firestations.service.domain.Firestations;

import java.util.List;

public interface FirestationsService {



    public Firestations getFirestationById(long id) throws Exception;

    public List<Firestations> getAllFirestations() throws Exception;

    public List<Firestations> getFirestationsByNumber(Long number);

    public Firestations getFirestationByAddress(String address);

    public Firestations createFirestation(Firestations firestation);

    public Firestations updateFirestation(Firestations updateFirestation);

    public Firestations deleteFirestation(Long id);


}
