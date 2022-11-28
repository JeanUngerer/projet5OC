package com.SafetyNet.Alerts.specificsEndpoints.service.domain;

import com.SafetyNet.Alerts.utils.FullNameAge;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Children {

    List<FullNameAge> children;

    List<FullNameAge> otherHomeMembers;
}
