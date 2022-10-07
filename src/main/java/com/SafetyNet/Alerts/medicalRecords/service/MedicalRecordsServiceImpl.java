package com.SafetyNet.Alerts.medicalRecords.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;


@Service
public class MedicalRecordsServiceImpl implements MedicalRecordsService {

	private final LiveDatas liveDatas;

	@Autowired
	public MedicalRecordsServiceImpl(LiveDatas liveDatas){
		this.liveDatas = liveDatas;
	}
	
	@Override
	public MedicalRecords getMedicalRecordsById(long id)
	{
		return liveDatas.getMedicalRecordById(id);
	}
	
	@Override
	public List<MedicalRecords> getAllMedicalRecords() {
		return new ArrayList<MedicalRecords>(liveDatas.getAllMedicalRecords().values());
	}
	

	
	@Override
	public MedicalRecords deleteMedicalRecord(Long id) {
		MedicalRecords removed = liveDatas.getMedicalRecordById(id);
		liveDatas.removeMedicalRecord(id);
		return removed;
	}
	
	@Override
	public MedicalRecords createMedicalRecords(String firstName, String lastName, ZonedDateTime birthdate, List<String> medications) {
		Long index = liveDatas.getMedicalRecordsIndex() + 1;
		MedicalRecords record = new MedicalRecords();
		record.setId(index);
		record.setMedications(medications);
		record.setFirstName(firstName);
		record.setLastName(lastName);
		record.setBirthdate(birthdate);
		liveDatas.setMedicalRecordsIndex(index);

		liveDatas.putMedicalRecord(index, record);
		return record;
		
	}
	
	@Override
	public MedicalRecords updateMedicalRecords(Long id, String firstName, String lastName, ZonedDateTime birthdate, List<String> medications) {
		MedicalRecords updatedMedicalRecord = liveDatas.getMedicalRecordById(id);
		updatedMedicalRecord.setMedications(medications);
		updatedMedicalRecord.setFirstName(firstName);
		updatedMedicalRecord.setLastName(lastName);
		updatedMedicalRecord.setBirthdate(birthdate);

		liveDatas.putMedicalRecord(id, updatedMedicalRecord);
		return updatedMedicalRecord;
	}

}
