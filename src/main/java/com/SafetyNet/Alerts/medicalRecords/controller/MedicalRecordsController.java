package com.SafetyNet.Alerts.medicalRecords.controller;

import com.SafetyNet.Alerts.dtos.MedicalRecordsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface MedicalRecordsController {

	ResponseEntity<MedicalRecordsDTO> getMedicalRecordsById(Long id)
			throws Exception;

	ResponseEntity<List<MedicalRecordsDTO>> getAllMedicalRecords() throws Exception;

	ResponseEntity<MedicalRecordsDTO> createMedicalRecords(@RequestBody MedicalRecordsDTO dto);

	ResponseEntity<MedicalRecordsDTO> updateMedicalRecords(@PathVariable Long id, @RequestBody MedicalRecordsDTO dto);

	ResponseEntity<String> deleteMedicalRecords(@PathVariable Long id);
}
