package com.SafetyNet.Alerts.firestations.service;

import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import com.SafetyNet.Alerts.firestations.service.domain.Firestations;
import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FirestationsServiceImpl implements FirestationsService{

    private final LiveDatas liveDatas;

    @Autowired
    public FirestationsServiceImpl(LiveDatas liveDatas){
        this.liveDatas = liveDatas;
    }

    @Override
    public Firestations getFirestationById(long id)
    {
        return liveDatas.getFirestationById(id);
    }

    @Override
    public List<Firestations> getAllFirestations() {
        return new ArrayList<Firestations>(liveDatas.getAllFirestations().values());
    }

    public List<Firestations> getFirestationsByNumber(Long number) {
        List<Firestations> allRecords = new ArrayList<Firestations>(liveDatas.getAllFirestations().values());

        return getNumberFilteredList(allRecords, number.toString());
    }

    public Firestations getFirestationByAddress(String address) {
        List<Firestations> allRecords = new ArrayList<Firestations>(liveDatas.getAllFirestations().values());

        return getAddressFilteredList(allRecords, address);
    }



    @Override
    public Firestations deleteFirestation(Long id) {
        Firestations removed = liveDatas.getFirestationById(id);
        liveDatas.removeFirestation(id);
        return removed;
    }

    @Override
    public Firestations createFirestation(Firestations firestation) {
        Long index = liveDatas.getFirestationsIndex() + 1;

        liveDatas.setFirestationsIndex(index);
        firestation.setId(index);
        liveDatas.putFirestation(index, firestation);
        return firestation;

    }

    @Override
    public Firestations updateFirestation(Firestations updateFirestation) {

        liveDatas.putFirestation(updateFirestation.getId(), updateFirestation);
        return updateFirestation;
    }

    private List<Firestations> getNumberFilteredList(List<Firestations> entryList, String number) {
        List<Firestations> firestationsFound = entryList.stream().filter(f -> number.equals(f.getStation())).collect(Collectors.toList());
        return firestationsFound;
    }

    private Firestations getAddressFilteredList(List<Firestations> entryList, String address) {
        List<Firestations> firestationsFound = entryList.stream().filter(f -> address.equals(f.getAddress())).collect(Collectors.toList());

        if (firestationsFound.size() == 1){
            return firestationsFound.get(0);
        }
        throw new IllegalArgumentException();
    }





}
