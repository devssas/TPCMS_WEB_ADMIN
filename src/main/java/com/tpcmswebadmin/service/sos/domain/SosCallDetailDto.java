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
public class SosCallDetailDto implements Serializable {

    private static final long serialVersionUID = 1394111587972609961L;

    private String staffName;

    private String remarkDate;

    private String remark;

}

