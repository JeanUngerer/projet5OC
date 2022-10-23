package com.SafetyNet.Alerts.medicalRecords.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
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

	public MedicalRecords getMedicalRecordByName(String firstName, String lastName)
	{
		List<MedicalRecords> allRecords = new ArrayList<MedicalRecords>(liveDatas.getAllMedicalRecords().values());

		return getNameFilteredList(allRecords, firstName, lastName);
	}

	@Override
	public List<MedicalRecords> getAllMedicalRecords() {
		return new ArrayList<>(liveDatas.getAllMedicalRecords().values());
	}
	

	
	@Override
	public MedicalRecords deleteMedicalRecord(Long id) {
		MedicalRecords removed = liveDatas.getMedicalRecordById(id);
		liveDatas.removeMedicalRecord(id);
		return removed;
	}
	
	@Override
	public MedicalRecords createMedicalRecords(MedicalRecords record) {
		Long index = liveDatas.getMedicalRecordsIndex() + 1;
		liveDatas.setMedicalRecordsIndex(index);
		record.setId(index);
		liveDatas.putMedicalRecord(index, record);
		return record;
		
	}
	
	@Override
	public MedicalRecords updateMedicalRecords(MedicalRecords updatedMedicalRecord) {


		liveDatas.putMedicalRecord(updatedMedicalRecord.getId(), updatedMedicalRecord);
		return updatedMedicalRecord;
	}

	private MedicalRecords getNameFilteredList(List<MedicalRecords> entryList, String firstName, String lastName) {
		return entryList.stream().filter(f -> firstName.compareTo(f.getFirstName()) > 0).filter(f -> lastName.compareTo(f.getLastName()) > 0).collect(Collectors.toList()).get(0);
	}

}
