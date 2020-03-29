package com.tpcmswebadmin.webpages.superadmin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreenInfoModel {

    private List<String> userTypes;

    private List<String> statuses;
}
