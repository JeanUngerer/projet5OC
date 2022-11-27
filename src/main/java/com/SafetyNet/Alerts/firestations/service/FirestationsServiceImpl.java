package com.SafetyNet.Alerts.firestations.service;

import com.SafetyNet.Alerts.constants.SafetyNetsErrorMessages;
import com.SafetyNet.Alerts.errorsType.ExceptionHandler;
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

    @Autowired
    private LiveDatas liveDatas;

    @Override
    public Firestations getFirestationById(long id)
    {
        try {
            return liveDatas.getFirestationById(id);
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    @Override
    public List<Firestations> getAllFirestations() {

        try {
            return new ArrayList<Firestations>(liveDatas.getAllFirestations().values());
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    public List<Firestations> getFirestationsByNumber(Long number) {
        try {
            List<Firestations> allRecords = new ArrayList<Firestations>(liveDatas.getAllFirestations().values());

            return getNumberFilteredList(allRecords, number.toString());
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }

    public Firestations getFirestationByAddress(String address) {
        try {
            List<Firestations> allRecords = new ArrayList<Firestations>(liveDatas.getAllFirestations().values());

            return getAddressFilteredList(allRecords, address);
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
        }
    }



    @Override
    public Firestations deleteFirestation(Long id) {


        try {
            Firestations removed = liveDatas.getFirestationById(id);
            liveDatas.removeFirestation(id);
            return removed;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NO_DELETE, e);
        }
    }

    @Override
    public Firestations createFirestation(Firestations firestation) {


        try {
            Long index = liveDatas.getFirestationsIndex() + 1;

            liveDatas.setFirestationsIndex(index);
            firestation.setId(index);
            liveDatas.putFirestation(index, firestation);
            return firestation;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NO_CREATION, e);
        }

    }

    @Override
    public Firestations updateFirestation(Firestations updateFirestation) {
        try {
            liveDatas.putFirestation(updateFirestation.getId(), updateFirestation);
            return updateFirestation;
        } catch (Exception e){
            throw new ExceptionHandler(SafetyNetsErrorMessages.NO_UPDATE, e);
        }
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
