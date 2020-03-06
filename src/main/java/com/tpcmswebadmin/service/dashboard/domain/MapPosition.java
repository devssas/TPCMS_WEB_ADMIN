package com.tpcmswebadmin.service.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapPosition implements Serializable {

    private static final long serialVersionUID = 9074332703522482688L;

    private String title;

    private String lat;

    private String lng;

    private String pin;
}
