package com.tpcmswebadmin.webpages.calender.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalenderEventModel {

    private String toWhom;

    private String day;

    private String time;

    private String title;

    private String actions;

}
