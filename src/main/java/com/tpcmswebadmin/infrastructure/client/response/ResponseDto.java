package com.tpcmswebadmin.infrastructure.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ResponseDto<T> {

    private String status;

    private String message;

    private DataDto<T> data;

    public ResponseDto() {
    }

    public ResponseDto(String status, String message, DataDto<T> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataDto<T> getData() {
        return data;
    }

    public void setData(DataDto<T> data) {
        this.data = data;
    }
}
