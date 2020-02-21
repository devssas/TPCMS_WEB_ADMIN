package com.tpcmswebadmin.service.administrator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorCardDto implements Serializable {

    private static final long serialVersionUID = -4144969337192041528L;

    private String officerName;

    private String commandCenter;

    private String rank;

    private String unit;

    private String officerId;

    private String expiryDate;

    private String image;

}
