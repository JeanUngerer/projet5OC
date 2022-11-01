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
public class PersonsDetailsWithMail {

    FullNameAge fullNameAge;

    String phoneNumber;

    String mail;

    List<String> medication;

    List<String> allergies;
}
