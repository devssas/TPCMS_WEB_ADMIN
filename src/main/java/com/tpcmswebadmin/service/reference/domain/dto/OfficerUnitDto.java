package com.tpcmswebadmin.service.reference.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficerUnitDto implements Serializable {

    private static final long serialVersionUID = -8571819301790031594L;

    private String commandCenterCode;

    private String commandCenter;

    private String unitCode;

    private String unitColorCode;

    private String unitDescription;

    private String unitId;

    private String unitLogo1;

    private String unitLogo2;

    private String unitNumber;

}
