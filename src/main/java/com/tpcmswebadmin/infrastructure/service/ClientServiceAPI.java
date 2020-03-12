package com.tpcmswebadmin.infrastructure.service;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ClientServiceAPI<T, K, E> {

    /**
     * Service interface for client calls. Each of the method should be implemented in order to make call to client.
     * Parameter order and information is as below:
     *
     * T = API Response DTO
     * K = Model or Session User Object
     * E = Client Request VO
     *
     */

    ResponseAPIDto<T> getResponseDto(HttpServletRequest request);

    ResponseAPIDto<T> prepareResponseDto(List<T> arrayList, boolean status, TPEngineResponse response);

    TPEngineResponse makeClientCall(K model);

    void setCredentials(E requestVO);

    List<String> setTableColumnNames();

}
