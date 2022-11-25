package com.SafetyNet.Alerts.medicalRecords.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.SafetyNet.Alerts.constants.SafetyNetsErrorMessages;
import com.SafetyNet.Alerts.errorsType.ExceptionHandler;
import com.SafetyNet.Alerts.liveDatas.service.LiveDatas;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;


@Service
@Slf4j
public class MedicalRecordsServiceImpl implements MedicalRecordsService {
	@Autowired
	private LiveDatas liveDatas;

	/*@Autowired
	public MedicalRecordsServiceImpl(LiveDatas liveDatas){
			this.liveDatas = liveDatas;
	}*/
	
	@Override
	public MedicalRecords getMedicalRecordsById(long id)
	{

		try {
			return liveDatas.getMedicalRecordById(id);
		} catch (Exception e){
			throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
		}
	}

	public MedicalRecords getMedicalRecordByName(String firstName, String lastName)
	{

		try {
			log.info("GET MED RECORD BY NAME");
			List<MedicalRecords> allRecords = new ArrayList<MedicalRecords>(liveDatas.getAllMedicalRecords().values());

			return getNameFilteredList(allRecords, firstName, lastName);
		} catch (Exception e){
			log.error("error computing medical record by name");
			throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
		}
	}

	@Override
	public List<MedicalRecords> getAllMedicalRecords() {

		try {
			return new ArrayList<>(liveDatas.getAllMedicalRecords().values());
		} catch (Exception e){
			throw new ExceptionHandler(SafetyNetsErrorMessages.NOT_FOUND, e);
		}
	}
	

	
	@Override
	public MedicalRecords deleteMedicalRecord(Long id) {

		try {
			MedicalRecords removed = liveDatas.getMedicalRecordById(id);
			liveDatas.removeMedicalRecord(id);
			return removed;
		} catch (Exception e){
			throw new ExceptionHandler(SafetyNetsErrorMessages.NO_DELETE, e);
		}
	}
	
	@Override
	public MedicalRecords createMedicalRecords(MedicalRecords record) {

		try {
			Long index = liveDatas.getMedicalRecordsIndex() + 1;
			liveDatas.setMedicalRecordsIndex(index);
			record.setId(index);
			liveDatas.putMedicalRecord(index, record);
			return record;
		} catch (Exception e){
			throw new ExceptionHandler(SafetyNetsErrorMessages.NO_CREATION, e);
		}
		
	}
	
	@Override
	public MedicalRecords updateMedicalRecords(MedicalRecords updatedMedicalRecord) {



		try {
			liveDatas.putMedicalRecord(updatedMedicalRecord.getId(), updatedMedicalRecord);
			return updatedMedicalRecord;
		} catch (Exception e){
			throw new ExceptionHandler(SafetyNetsErrorMessages.NO_UPDATE, e);
		}
	}

	private MedicalRecords getNameFilteredList(List<MedicalRecords> entryList, String firstName, String lastName) {
		List<MedicalRecords> medicalRecordsFound = entryList.stream().filter(f -> lastName.equals(f.getLastName())).filter(f -> firstName.equals(f.getFirstName())).collect(Collectors.toList());
		if (medicalRecordsFound.size() > 0) {
			return medicalRecordsFound.get(0);
		}
		return null;
	}

}
