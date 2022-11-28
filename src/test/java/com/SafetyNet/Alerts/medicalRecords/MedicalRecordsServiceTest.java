package com.SafetyNet.Alerts.medicalRecords;

import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import com.SafetyNet.Alerts.medicalRecords.service.MedicalRecordsService;
import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;
import com.SafetyNet.Alerts.persons.service.PersonsService;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MedicalRecordsServiceTest {

    private static LiveDatas liveDatas;
    @Autowired
    MedicalRecordsService medicalRecordsService;


    @Autowired
    public MedicalRecordsServiceTest(LiveDatas liveDatas){
        this.liveDatas = liveDatas;
    }

    @BeforeAll
    public static void prepareDatas(){

    }

    @Test
    public void getMedicalRecordByIdTest() throws Exception {

        MedicalRecords medicalRecords = medicalRecordsService.getMedicalRecordsById(1);

        assertEquals(1, medicalRecords.getId());
        assertEquals("John", medicalRecords.getFirstName());
        assertEquals("Boyd", medicalRecords.getLastName());
    }

    @Test
    public void getMedicalRecordByNameTest() throws Exception {

        MedicalRecords medicalRecords = medicalRecordsService.getMedicalRecordByName("John", "Boyd");

        assertEquals(1, medicalRecords.getId());
        assertEquals("John", medicalRecords.getFirstName());
        assertEquals("Boyd", medicalRecords.getLastName());
    }

    @Test
    public void getAllMedicalRecordsTest() throws Exception{
        List<MedicalRecords> allMedicalRecords = medicalRecordsService.getAllMedicalRecords();

        MedicalRecords medicalRecords = allMedicalRecords.get(0);

        assertEquals(23, allMedicalRecords.size());
        assertEquals(1, medicalRecords.getId());
        assertEquals("John", medicalRecords.getFirstName());
        assertEquals("Boyd", medicalRecords.getLastName());
    }

    @Test
    public void updateMedicalRecordTest() throws  Exception{
        MedicalRecords medicalRecordsToUpdate = new MedicalRecords();
        medicalRecordsToUpdate.setId(Long.valueOf(1));
        medicalRecordsToUpdate.setMedications(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
        medicalRecordsToUpdate.setBirthdate("03/06/1985");
        medicalRecordsToUpdate.setLastName("Boyd");
        medicalRecordsToUpdate.setFirstName("John");
        medicalRecordsToUpdate.setAllergies(Arrays.asList("nillacilan"));


        medicalRecordsService.updateMedicalRecords(medicalRecordsToUpdate);

        MedicalRecords medicalRecordsUpdated = medicalRecordsService.getMedicalRecordByName("John", "Boyd");
        System.out.println(medicalRecordsUpdated.toString());

        assertEquals(1, medicalRecordsUpdated.getId());
        assertEquals("John", medicalRecordsUpdated.getFirstName());
        assertEquals("Boyd", medicalRecordsUpdated.getLastName());
        assertThat(medicalRecordsUpdated.getBirthdate().toString(), containsString("1985-03-06"));
    }
}
