package com.SafetyNet.Alerts.specificsEndpoints.service.domain;

import com.SafetyNet.Alerts.utils.PersonDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AddressInfo {

    String address;

    List<PersonDetails> Occupants;

    String firestationNumber;
}
