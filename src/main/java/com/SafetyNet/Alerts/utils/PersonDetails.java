package com.SafetyNet.Alerts.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PersonDetails {

    FullNameAge fullNameAge;

    String phoneNumber;

    List<String> medication;

    List<String> allergies;
}
