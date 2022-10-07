package com.SafetyNet.Alerts.medicalRecords.controller;

import com.SafetyNet.Alerts.dtos.MedicalRecordsDTO;
import org.springframework.http.ResponseEntity;


public interface MedicalRecordsController {

	ResponseEntity<MedicalRecordsDTO> getMedicalRecordsById(Long id)
			throws Exception;
}
