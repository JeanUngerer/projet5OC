package com.SafetyNet.Alerts.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class AddressesOccupants {

    String address;

    List<PersonDetails> occupants;
}
