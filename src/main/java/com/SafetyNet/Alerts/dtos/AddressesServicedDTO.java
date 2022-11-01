package com.SafetyNet.Alerts.dtos;

import com.SafetyNet.Alerts.utils.AddressesOccupants;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressesServicedDTO {

    String stationNumber;

    List<AddressesOccupants> homesCovered;
}
