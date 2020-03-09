package com.tpcmswebadmin.service.sos.domain;

import com.tpcmswebadmin.service.dashboard.domain.MapCenter;
import com.tpcmswebadmin.service.dashboard.domain.MapPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SosCallDetailDto implements Serializable {

    private static final long serialVersionUID = -1314155917177499262L;

    private MapCenter mapCenter;

    private List<MapPosition> data;
}
