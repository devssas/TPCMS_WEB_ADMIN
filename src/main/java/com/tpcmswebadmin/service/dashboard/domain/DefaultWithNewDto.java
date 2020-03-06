package com.tpcmswebadmin.service.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultWithNewDto {

    @JsonProperty(value = "default")
    private String defaultName;

    @JsonProperty(value = "new")
    private String newName;

}
