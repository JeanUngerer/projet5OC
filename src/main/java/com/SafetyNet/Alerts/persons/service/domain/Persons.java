package com.SafetyNet.Alerts.persons.service.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Persons {
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String zip;

    private String phone;

    private String email;
}
