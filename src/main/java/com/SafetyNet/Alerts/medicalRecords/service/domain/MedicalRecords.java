package com.SafetyNet.Alerts.medicalRecords.service.domain;



import java.time.ZonedDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MedicalRecords {
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private ZonedDateTime birthdate;
	
	private List<String> medications;

	private List<String> allergies;
	
}
