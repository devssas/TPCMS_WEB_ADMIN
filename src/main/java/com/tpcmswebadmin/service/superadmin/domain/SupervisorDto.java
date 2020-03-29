package com.tpcmswebadmin.service.superadmin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDto implements Serializable {

    private static final long serialVersionUID = -8253074461371388071L;

    private String adminId;

    private String adminName;

    private String address;

    private String city;

    private String accessRole;

    private String lastLogin;

    private String status;

    private String actions;

}
