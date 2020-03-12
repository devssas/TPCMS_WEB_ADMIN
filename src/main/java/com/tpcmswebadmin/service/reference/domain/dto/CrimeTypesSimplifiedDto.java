package com.tpcmswebadmin.service.reference.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrimeTypesSimplifiedDto {

    private String id;

    private String title;

    private String description;

}
