package com.tpcmswebadmin.webpages.sos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SosCallModel {

    private String id;

    private String location;

    private String phoneNumber;

    private String username;

    private String requestDate;

    private String requestSerialNumber;

    private String remarks;

    private String status;

}
