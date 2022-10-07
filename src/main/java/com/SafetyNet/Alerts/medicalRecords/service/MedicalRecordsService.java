package com.SafetyNet.Alerts.medicalRecords.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;


public interface MedicalRecordsService {
	
	public MedicalRecords getMedicalRecordsById(long id) throws Exception;

	public List<MedicalRecords> getAllMedicalRecords() throws Exception;

	public MedicalRecords createMedicalRecords(String firstName, String lastName, ZonedDateTime birthdate, List<String> medications);

	public MedicalRecords updateMedicalRecords(Long id, String firstName, String lastName, ZonedDateTime birthdate, List<String> medications);

	public MedicalRecords deleteMedicalRecord(Long id);

}
