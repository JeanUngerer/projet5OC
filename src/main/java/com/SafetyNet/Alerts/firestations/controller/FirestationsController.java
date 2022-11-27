package com.SafetyNet.Alerts.firestations.controller;

import com.SafetyNet.Alerts.dtos.FirestationsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FirestationsController {

    ResponseEntity<FirestationsDTO> getFirestationsById(Long id)
            throws Exception;

    ResponseEntity<List<FirestationsDTO>> getAllFirestations() throws Exception;

    ResponseEntity<FirestationsDTO> createFirestations(@RequestBody FirestationsDTO dto);

    ResponseEntity<FirestationsDTO> updateFirestations(@RequestBody FirestationsDTO dto);

    ResponseEntity<String> deleteFirestations(@PathVariable Long id);
}
