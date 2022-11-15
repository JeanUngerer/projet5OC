package com.SafetyNet.Alerts.firestations.controller;

import com.SafetyNet.Alerts.dtos.FirestationsDTO;
import com.SafetyNet.Alerts.dtos.PersonsDTO;
import com.SafetyNet.Alerts.firestations.service.FirestationsService;
import com.SafetyNet.Alerts.firestations.service.domain.Firestations;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
public class FirestationsControllerImpl {


    @Autowired
    private FirestationsService firestationsService;

    private ModelMapper modelMapper = new ModelMapper();


    @GetMapping("/firestations/{id}")
    public ResponseEntity<FirestationsDTO> getFirestationsById(@PathVariable Long id) throws Exception {
        Firestations record = firestationsService.getFirestationById(id);
        return new ResponseEntity<>(modelMapper.map(record, FirestationsDTO.class), HttpStatus.OK);

    }

    @GetMapping("/firestations")
    public ResponseEntity<List<FirestationsDTO>> getAllFirestations() throws Exception {
        List<Firestations> records = firestationsService.getAllFirestations();
        List<FirestationsDTO> result = Arrays.asList(modelMapper.map(records, FirestationsDTO[].class));
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping(path = "/firestations", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FirestationsDTO> createFirestations(@RequestBody FirestationsDTO dto) {
        Firestations record = firestationsService.createFirestation(
                modelMapper.map(dto, Firestations.class)
        );

        return ResponseEntity.ok().body(modelMapper.map(record, FirestationsDTO.class));
    }

    @PutMapping(path = "/firestations/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonsDTO> updateFirestations(@RequestBody PersonsDTO dto) {
        Firestations record = firestationsService.updateFirestation(
                modelMapper.map(dto, Firestations.class)
        );

        return ResponseEntity.ok().body(modelMapper.map(record, PersonsDTO.class));
    }

    @DeleteMapping("/firestations/{id}")
    public ResponseEntity<String> deleteFirestations(@PathVariable Long id) {
        firestationsService.deleteFirestation(id);
        return new ResponseEntity<>("Station deleted", HttpStatus.OK);
    }
}
