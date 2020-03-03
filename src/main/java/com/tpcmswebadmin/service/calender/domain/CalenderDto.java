package com.tpcmswebadmin.service.calender.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalenderDto implements Serializable {

    private static final long serialVersionUID = -6192765017688184563L;

    private String toWhom;

    private String day;

    private String time;

    private String title;

    private String actions;

}
