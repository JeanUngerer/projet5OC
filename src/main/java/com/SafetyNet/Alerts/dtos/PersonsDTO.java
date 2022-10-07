package com.SafetyNet.Alerts.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class PersonsDTO {

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String zip;

    private String phone;

    private String email;
}
