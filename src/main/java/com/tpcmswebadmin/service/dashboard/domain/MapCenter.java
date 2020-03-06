package com.tpcmswebadmin.service.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class MapCenter implements Serializable {

    private static final long serialVersionUID = 8842486662722363874L;

    private String lat;

    private String lng;

    public MapCenter() {
        this.lat = "32.887211";
        this.lng = "13.191338";
    }
}
