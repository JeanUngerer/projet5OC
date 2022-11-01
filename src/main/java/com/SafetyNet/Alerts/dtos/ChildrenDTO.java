package com.SafetyNet.Alerts.dtos;


import com.SafetyNet.Alerts.utils.FullNameAge;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChildrenDTO {

    List<FullNameAge> children;

    List<FullNameAge> otherHomeMembers;
}
