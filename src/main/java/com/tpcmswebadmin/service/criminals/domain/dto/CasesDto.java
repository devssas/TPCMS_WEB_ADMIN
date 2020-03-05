package com.tpcmswebadmin.service.criminals.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CasesDto implements Serializable {

    private static final long serialVersionUID = -3350024529200209952L;

    private String nationalId;

    private String criminalName;

    private String criminalsCode;

    private String address;

    private String city;

    private String status;

    private String actions;

}
