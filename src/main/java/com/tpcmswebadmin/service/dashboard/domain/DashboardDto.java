package com.tpcmswebadmin.service.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto implements Serializable {

    private static final long serialVersionUID = 5778719521103352322L;

    private MapCenter mapCenter;

    private List<MapPosition> data;

    private DefaultWithNewDto notifications;

    private DefaultDto permits;

    private DefaultDto cases;

    private DefaultDto sos;

    private DefaultWithNewDto sosDetail;

}
