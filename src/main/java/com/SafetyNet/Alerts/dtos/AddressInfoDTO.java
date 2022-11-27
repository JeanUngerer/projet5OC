package com.SafetyNet.Alerts.dtos;

import com.SafetyNet.Alerts.utils.PersonDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AddressInfoDTO {

    String address;

    List<PersonDetails> Occupants;

    String firestationNumber;
}
