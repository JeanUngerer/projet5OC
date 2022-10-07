package com.SafetyNet.Alerts.liveDatas.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Service
@Slf4j
public class LiveDatas {
    private final RestTemplate restTemplate;

    private static Map<Long, MedicalRecords> medicalRecords;
    private static Long medicalRecordsIndex;

    public LiveDatas(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.medicalRecordsIndex = Long.valueOf(0);
    }

    public void getInitialDatas() {
        String url = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
        String dataJSON = this.restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        log.info("FETCHED INITIAL DATAS : " + dataJSON);

        try {
            JsonNode actualObj = mapper.readTree(dataJSON);
            JsonNode medRecordsNode = actualObj.get("medicalRecords");
            log.info("FETCHED INITIAL MEDICAL RECORDS : " + medRecordsNode);

        } catch (Throwable e) {
            log.error("Error during the initilization of datas : " + e);
            throw new RuntimeException("Error during the initilization of datas", e);
        }
    }

    public Map<Long, MedicalRecords> getAllMedicalRecords() {
        return this.medicalRecords;
    }

    public MedicalRecords getMedicalRecordById(Long id){
        return this.medicalRecords.get(id);
    }

    public void putMedicalRecord(Long id, MedicalRecords record){
        this.medicalRecords.put(id, record);
    }

    public void removeMedicalRecord(Long id){
        this.medicalRecords.remove(id);
    }



    public Long getMedicalRecordsIndex(){
        return this.medicalRecordsIndex;
    }

    public void setMedicalRecordsIndex(Long index){
        this.medicalRecordsIndex = index;
    }
}
