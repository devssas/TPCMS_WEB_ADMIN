package com.tpcmswebadmin.service.reference.service;

import com.ssas.tpcms.engine.vo.response.AllowedWeaponTypesConfigResponseVO;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReferenceService {

    private final TPCMSClient tpcmsClient;

    public List<String> getWeaponTypes() {
        try {
            return makeWeaponList(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getAllowedWeaponTypesConfigMapping(null, null));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. ");
        }

        return Collections.emptyList();
    }

    private List<String> makeWeaponList(AllowedWeaponTypesConfigResponseVO[] allowedWeaponTypesConfigResponseVO) {
        return Arrays.stream(allowedWeaponTypesConfigResponseVO)
                .map(AllowedWeaponTypesConfigResponseVO::getWeaponTypeName_Ar)
                .collect(Collectors.toList());
    }
}
