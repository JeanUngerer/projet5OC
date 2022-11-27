package com.SafetyNet.Alerts.specificsEndpoints.service.domain;

import com.SafetyNet.Alerts.persons.service.domain.Persons;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class StationPersonsCoverage {

    private List<Persons> personsCovered;

    private long adultsSouls;

    private long childrenSouls;
}
