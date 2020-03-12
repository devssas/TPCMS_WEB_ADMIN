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
public class ResponseAPIDto<T> {

    private String status;

    private String message;

    private DataDto<T> data;

    public ResponseAPIDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

}
