package com.SafetyNet.Alerts.dtos;

import com.SafetyNet.Alerts.persons.service.domain.Persons;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class StationPersonsCoverageDTO {

    private List<Persons> personsCovered;

    private Long adultsSouls;

    private Long childrenSouls;

}
