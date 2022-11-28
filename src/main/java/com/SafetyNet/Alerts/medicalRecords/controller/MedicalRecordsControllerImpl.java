package com.SafetyNet.Alerts.medicalRecords.controller;


import com.SafetyNet.Alerts.dtos.MedicalRecordsDTO;
import com.SafetyNet.Alerts.medicalRecords.service.MedicalRecordsService;
import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("${spring.data.rest.base-path}")
public class MedicalRecordsControllerImpl implements MedicalRecordsController {

	@Autowired
	private MedicalRecordsService medicalRecordsService;

	private ModelMapper modelMapper = new ModelMapper();


	@GetMapping("/medicalrecords/{id}")
	public ResponseEntity<MedicalRecordsDTO> getMedicalRecordsById(@PathVariable Long id) throws Exception {
		MedicalRecords record = medicalRecordsService.getMedicalRecordsById(id);
		log.info("USED Get : /medicalrecords/" + id);
		return new ResponseEntity<>(modelMapper.map(record, MedicalRecordsDTO.class), HttpStatus.OK);
		
	}

	@GetMapping("/medicalrecords")
	public ResponseEntity<List<MedicalRecordsDTO>> getAllMedicalRecords() throws Exception {
		List<MedicalRecords> records = medicalRecordsService.getAllMedicalRecords();
		List<MedicalRecordsDTO> result = Arrays.asList(modelMapper.map(records, MedicalRecordsDTO[].class));
		log.info("USED Get : /medicalrecords");
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping(path = "/medicalrecords", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedicalRecordsDTO> createMedicalRecords(@RequestBody MedicalRecordsDTO dto) {
		MedicalRecords record = medicalRecordsService.createMedicalRecords(
				modelMapper.map(dto, MedicalRecords.class)
		);
		log.info("USED Post : /medicalrecords");

		return ResponseEntity.ok().body(modelMapper.map(record, MedicalRecordsDTO.class));
	}

	@PutMapping(path = "/medicalrecords/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedicalRecordsDTO> updateMedicalRecords(@PathVariable Long id, @RequestBody MedicalRecordsDTO dto) {
		MedicalRecords record = medicalRecordsService.updateMedicalRecords(
				modelMapper.map(dto, MedicalRecords.class)
		);
		log.info("USED Put : /medicalrecords/" + dto);
		return ResponseEntity.ok().body(modelMapper.map(record, MedicalRecordsDTO.class));
	}

	@DeleteMapping("/medicalrecords/{id}")
	public ResponseEntity<String> deleteMedicalRecords(@PathVariable Long id) {
		MedicalRecords removed = medicalRecordsService.deleteMedicalRecord(id);
		log.info("USED Delete : /medicalrecords/" + id);
		return new ResponseEntity<>("The medical record from " + removed.getFirstName() + " " + removed.getLastName() +  " has been deleted", HttpStatus.OK);
	}

}
