package com.SafetyNet.Alerts.persons.controller;

import com.SafetyNet.Alerts.dtos.PersonsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface PersonsController {
	ResponseEntity<PersonsDTO> getPersonsById(Long id)
			throws Exception;

	ResponseEntity<List<PersonsDTO>> getAllPersons() throws Exception;

	ResponseEntity<PersonsDTO> createPersons(@RequestBody PersonsDTO dto);

	ResponseEntity<PersonsDTO> updatePersons(@RequestBody PersonsDTO dto);

	ResponseEntity<String> deletePersons(@PathVariable Long id);
}
