package com.SafetyNet.Alerts.liveDatas.service;

import com.SafetyNet.Alerts.constants.DataUrls;
import com.SafetyNet.Alerts.firestations.service.FirestationsService;
import com.SafetyNet.Alerts.firestations.service.domain.Firestations;

import com.SafetyNet.Alerts.medicalRecords.service.MedicalRecordsService;
import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;
import com.SafetyNet.Alerts.persons.service.PersonsService;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jsoniter.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.SafetyNet.Alerts.utils.JsonReader.readJsonFromUrl;



@Slf4j
@Component
public class DataInitiatior {

    private ModelMapper modelMapper = new ModelMapper();

    DataInitiatior(){
    }

    @Autowired
    PersonsService personsService;

    @Autowired
    FirestationsService firestationsService;
    @Autowired
    MedicalRecordsService medicalRecordsService;

    @PostConstruct
    public void getInitialDatas() {
        String url = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
        JSONObject dataJSON = null;
        List<Object> testObjectList = null;
        try {
            dataJSON = readJsonFromUrl(DataUrls.INITIAL_URL);
        } catch (IOException e) {
            log.error("Error while fetching initial datas : " + e.getMessage());
            throw new RuntimeException("Error while fetching initial datas", e);
        }
        log.info("FETCHED INITIAL DATAS : ");
        try {

            ObjectMapper objectMapper = JsonMapper.builder()
                    .addModule(new JavaTimeModule())
                    .build();

            JSONArray personsArray = dataJSON.getJSONArray("persons");
            JSONArray medicalRecordsArray = dataJSON.getJSONArray("medicalrecords");
            JSONArray firestationsArray = dataJSON.getJSONArray("firestations");


            personsArray.forEach(json -> {
                try {
                    personsService.createPerson(objectMapper.readValue(json.toString(), Persons.class));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });

            firestationsArray.forEach(json -> {
                try {
                    firestationsService.createFirestation(objectMapper.readValue(json.toString(), Firestations.class));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });

            medicalRecordsArray.forEach(json -> {
                try {

                    MedicalRecords medicalRecords = objectMapper.readerFor(MedicalRecords.class).readValue(json.toString());
                    medicalRecordsService.createMedicalRecords(medicalRecords);

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Throwable e) {
            log.error("Error during the initilization of datas : " + e);
            throw new RuntimeException("Error during the initilization of datas", e);
        }
    }

    private ZonedDateTime  convertStringToZonedDateTime(String fecha){
        final DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate date = LocalDate.parse(fecha, formatter);

        ZonedDateTime result = date.atStartOfDay(ZoneId.systemDefault());
        return result;
    }
}



