package com.tpcmswebadmin.service.missionpermits.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MissionPermitsDto implements Serializable {

    private static final long serialVersionUID = -7774865472197682014L;

    private String permitId;

    private String username;

    private String mobileNumber;

    private String missionQrCode;

    private String city;

    private String expiryDate;

    private String status;

    private String actions;

}
