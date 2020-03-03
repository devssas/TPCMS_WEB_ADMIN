package com.tpcmswebadmin.infrastructure.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class ResponseDto<T> {

    private String status;

    private String message;

    private DataDto<T> data;

    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

}
