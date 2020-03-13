package com.tpcmswebadmin.infrastructure.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSearchDataDto<T> {

    private String status;

    private String message;

    private ResponseTBodyDto<T> data;
}
