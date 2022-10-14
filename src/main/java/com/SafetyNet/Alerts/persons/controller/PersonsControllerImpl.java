package com.SafetyNet.Alerts.persons.controller;


import com.SafetyNet.Alerts.dtos.MedicalRecordsDTO;
import com.SafetyNet.Alerts.dtos.PersonsDTO;
import com.SafetyNet.Alerts.medicalRecords.service.MedicalRecordsService;
import com.SafetyNet.Alerts.medicalRecords.service.domain.MedicalRecords;
import com.SafetyNet.Alerts.persons.service.PersonsService;
import com.SafetyNet.Alerts.persons.service.domain.Persons;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
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

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("${spring.data.rest.base-path}")
public class PersonsControllerImpl implements PersonsController {


	@Autowired
	private PersonsService personsService;

	private ModelMapper modelMapper;


	@GetMapping("/persons/{id}")
	public ResponseEntity<PersonsDTO> getPersonsById(@PathVariable Long id) throws Exception {
		Persons record = personsService.getPersonById(id);
		return new ResponseEntity<>(modelMapper.map(record, PersonsDTO.class), HttpStatus.OK);

	}

	@GetMapping("/persons")
	public ResponseEntity<List<PersonsDTO>> getAllPersons() throws Exception {
		List<Persons> records = personsService.getAllPersons();
		List<PersonsDTO> result = Arrays.asList(modelMapper.map(records, PersonsDTO[].class));
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping(path = "/persons", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<PersonsDTO> createPersons(@RequestBody PersonsDTO dto) {
		Persons record = personsService.createPerson(
				dto.getFirstName(),
				dto.getLastName(),
				dto.getAddress(),
				dto.getCity(),
				dto.getPhone(),
				dto.getEmail()
		);

		return ResponseEntity.ok().body(modelMapper.map(record, PersonsDTO.class));
	}

	@PutMapping(path = "/persons/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<PersonsDTO> updatePersons(@PathVariable Long id, @RequestBody PersonsDTO dto) {
		Persons record = personsService.updatePerson(
				id,
				dto.getFirstName(),
				dto.getLastName(),
				dto.getAddress(),
				dto.getCity(),
				dto.getPhone(),
				dto.getEmail()
		);

		return ResponseEntity.ok().body(modelMapper.map(record, PersonsDTO.class));
	}

	@DeleteMapping("/persons/{id}")
	public ResponseEntity<String> deletePersons(@PathVariable Long id) {
		Persons removed = personsService.deletePerson(id);
		return new ResponseEntity<>("The person entry for " + removed.getFirstName() + " " + removed.getLastName() +  " has been deleted", HttpStatus.OK);
	}

}
