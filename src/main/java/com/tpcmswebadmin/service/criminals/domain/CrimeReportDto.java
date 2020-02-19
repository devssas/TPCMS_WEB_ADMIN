package com.tpcmswebadmin.service.criminals.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CrimeReportDto implements Serializable {

    private static final long serialVersionUID = -6597756178901602557L;

    private String nationalId;

    private String criminalName;

    private String address;

    private String city;

    private String state;

    private String wantedBy;

    private String status;

    private String actions;

}
