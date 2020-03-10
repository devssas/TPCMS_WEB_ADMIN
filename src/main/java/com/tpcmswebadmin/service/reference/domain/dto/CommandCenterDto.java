package com.tpcmswebadmin.service.reference.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandCenterDto {

    private String colorCode;

    private String commandCenterCode;

    private String commandCenterDescription;

    private String commandCenterId;

    private String commandCenterLogo1;

    private String commandCenterLogo2;

}

