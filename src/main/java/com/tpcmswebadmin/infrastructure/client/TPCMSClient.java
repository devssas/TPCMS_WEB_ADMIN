package com.tpcmswebadmin.infrastructure.client;

import com.ssas.tpcms.engine.services.TPCMSCoreServicesServiceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TPCMSClient {

    @Bean
    public TPCMSCoreServicesServiceLocator tpcmsWebAdminClient() {
        return new TPCMSCoreServicesServiceLocator();
    }

}
