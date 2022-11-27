package com.SafetyNet.Alerts.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class MedicalRecordsDTO {

    private long id;

    private String firstName;

    private String lastName;

    private ZonedDateTime birthdate;

    private List<String> medications;

    private List<String> allergies;
}
