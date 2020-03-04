package com.tpcmswebadmin.infrastructure.service;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;

public interface ClientCreateServiceAPI<T, K> {

    /**
     * Service interface for client to make create calls. Each of the method should be implemented in order to make call to client.
     * Parameter order and information is as below:
     *
     * T = Model
     * K = Client request
     *
     */

    TPEngineResponse create(T model, LoginUserDo loginUserDo);

    void setFields(T model, K requestVO);

    void setCredentials(K request, LoginUserDo loginUserDo);

}
