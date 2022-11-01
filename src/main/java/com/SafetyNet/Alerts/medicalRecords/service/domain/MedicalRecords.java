package com.SafetyNet.Alerts.medicalRecords.service.domain;



import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MedicalRecords {
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private ZonedDateTime birthdate;
	
	private List<String> medications;

	private List<String> allergies;

	@JsonSetter
	public void setBirthdate( String birth){
		birthdate = convertStringToZonedDateTime(birth);
	}

	private ZonedDateTime  convertStringToZonedDateTime(String fecha){
		final DateTimeFormatter formatter
				= DateTimeFormatter.ofPattern("MM/dd/yyyy");

		LocalDate date = LocalDate.parse(fecha, formatter);

		ZonedDateTime result = date.atStartOfDay(ZoneId.systemDefault());
		return result;
	}
	
}
