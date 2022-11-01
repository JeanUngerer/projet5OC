package com.SafetyNet.Alerts.specificsEndpoints.service.domain;

import com.SafetyNet.Alerts.utils.PersonsDetailsWithMail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PersonsDetailsByNameAge {

    List<PersonsDetailsWithMail> allCorresponding;
}
