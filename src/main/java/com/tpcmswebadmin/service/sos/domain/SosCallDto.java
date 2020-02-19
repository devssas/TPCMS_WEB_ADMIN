package com.tpcmswebadmin.service.sos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SosCallDto implements Serializable {

    private static final long serialVersionUID = -2681521417090531244L;

    private String requestNumber;

    private String requestDate;

    private String userId;

    private String emergencyLocation;

    private String phone;

    private String remarks;

    private String status;

    private String actions;
}
