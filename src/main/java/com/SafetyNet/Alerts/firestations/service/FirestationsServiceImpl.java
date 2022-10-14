package com.SafetyNet.Alerts.firestations.service;

import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import com.SafetyNet.Alerts.firestations.service.domain.Firestations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        liveDatas.putFirestation(index, firestation);
        return firestation;

    }

    @Override
    public Firestations updateFirestation(Firestations updateFirestation) {

        liveDatas.putFirestation(updateFirestation.getId(), updateFirestation);
        return updateFirestation;
    }

}
