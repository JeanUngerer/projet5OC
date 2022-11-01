package com.SafetyNet.Alerts.dtos;

import com.SafetyNet.Alerts.utils.PersonDetails;
import com.SafetyNet.Alerts.utils.PersonsDetailsWithMail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PersonsDetailsByNameAgeDTO {

    List<PersonsDetailsWithMail> allCorresponding;
}
