package com.SafetyNet.Alerts.medicalRecords.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;


public interface MedicalRecordsService {
	
	public MedicalRecords getMedicalRecordsById(long id) throws Exception;

	public MedicalRecords getMedicalRecordByName(String firstName, String lastName);
	public List<MedicalRecords> getAllMedicalRecords() throws Exception;

	public MedicalRecords createMedicalRecords(MedicalRecords record);

	public MedicalRecords updateMedicalRecords(MedicalRecords updatedMedicalRecord);

	public MedicalRecords deleteMedicalRecord(Long id);

}
