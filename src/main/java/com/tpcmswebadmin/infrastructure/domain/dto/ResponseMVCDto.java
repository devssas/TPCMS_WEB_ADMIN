package com.tpcmswebadmin.infrastructure.domain.dto;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMVCDto {

    private boolean result;

    private String message;

    public static ResponseMVCDto prepareResponse(TPEngineResponse response) {
        if(response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
            return ResponseMVCDto.builder()
                    .message(null)
                    .result(true)
                    .build();
        } else {
            return ResponseMVCDto.builder()
                    .message(response.getResponseCodeVO().getResponseCode() + " - " + response.getResponseCodeVO().getResponseValue())
                    .result(false)
                    .build();
        }
    }

}
