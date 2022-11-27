package com.SafetyNet.Alerts.specificsEndpoints.service.domain;

import com.SafetyNet.Alerts.utils.AddressesOccupants;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressesServiced {

    String stationNumber;

    List<AddressesOccupants> homesCovered;
}
