package com.tpcmswebadmin.infrastructure.client;

import com.ssas.tpcms.engine.services.TPCMSCoreServicesServiceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TPCMSClients {

    @Bean
    public TPCMSCoreServicesServiceLocator dinarPayWebAdminClient() {
        return new TPCMSCoreServicesServiceLocator();
    }

}
