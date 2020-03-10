package com.tpcmswebadmin.service.reference.service;

import com.ssas.tpcms.engine.vo.response.AllowedWeaponTypesConfigResponseVO;
import com.ssas.tpcms.engine.vo.response.CommandCenterResponseVO;
import com.ssas.tpcms.engine.vo.response.NatureOfAnnouncementResponseVO;
import com.ssas.tpcms.engine.vo.response.OfficerUnitResponseVO;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.service.reference.domain.dto.CommandCenterDto;
import com.tpcmswebadmin.service.reference.domain.dto.NatureOfAnnouncementDto;
import com.tpcmswebadmin.service.reference.domain.dto.OfficerUnitDto;
import com.tpcmswebadmin.service.reference.domain.enums.ClientStatus;
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

    public List<String> getClientStatus() {
        return Arrays.stream(ClientStatus.values()).map(ClientStatus::getClientName).collect(Collectors.toList());
    }

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

    public List<OfficerUnitDto> getOfficerUnitList() {
        try {
            return makeOfficerUnitDtoList(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficerUnitsMapping(null, null));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. ");
        }

        return Collections.emptyList();
    }

    private List<OfficerUnitDto> makeOfficerUnitDtoList(OfficerUnitResponseVO[] officerUnitsMapping) {
        return Arrays.stream(officerUnitsMapping)
                .map(ReferenceService::makeOfficerUnitDto)
                .collect(Collectors.toList());
    }

    private static OfficerUnitDto makeOfficerUnitDto(OfficerUnitResponseVO officerUnitResponseVO) {
        return OfficerUnitDto.builder()
                .commandCenter(officerUnitResponseVO.getCommandCenterId())
                .commandCenterCode(officerUnitResponseVO.getCommandCenterCode())
                .unitCode(officerUnitResponseVO.getUnitCode())
                .unitColorCode(officerUnitResponseVO.getUnitColorCode())
                .unitDescription(officerUnitResponseVO.getUnitDescription())
                .unitId(officerUnitResponseVO.getUnitId())
                .unitLogo1(ImageUtility.convertToBase64image(officerUnitResponseVO.getUnitLogo1()))
                .unitLogo2(ImageUtility.convertToBase64image(officerUnitResponseVO.getUnitLogo2()))
                .unitNumber(officerUnitResponseVO.getUnitNumber())
                .build();
    }

    public List<NatureOfAnnouncementDto> getNatureOfAnnouncement() {
        try {
            return makeNatureOfAnnouncementDtoList(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getNatureofAnnouncementsConfig(null, null, null));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. ");
        }

        return Collections.emptyList();
    }

    private List<NatureOfAnnouncementDto> makeNatureOfAnnouncementDtoList(NatureOfAnnouncementResponseVO[] natureOfAnnouncementResponseVOS) {
        return Arrays.stream(natureOfAnnouncementResponseVOS)
                .map(ReferenceService::makeNatureOfAnnouncement)
                .collect(Collectors.toList());
    }

    private static NatureOfAnnouncementDto makeNatureOfAnnouncement(NatureOfAnnouncementResponseVO natureOfAnnouncementResponseVOS) {
        return NatureOfAnnouncementDto.builder()
                .natureOfAnnouncement(natureOfAnnouncementResponseVOS.getNatureOfAnnouncement())
                .natureOfAnnouncementCode(natureOfAnnouncementResponseVOS.getNatureOfAnnouncementCode())
                .natureOfAnnouncementDescription(natureOfAnnouncementResponseVOS.getNatureOfAnnouncementDescription())
                .natureOfAnnouncementId(natureOfAnnouncementResponseVOS.getNatureOfAnnouncementId())
                .build();
    }

    public List<CommandCenterDto> getCommandCenter() {
        try {
            return makeCommandCenterDtoList(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getCommandCenterMapping());
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. ");
        }

        return Collections.emptyList();
    }

    private List<CommandCenterDto> makeCommandCenterDtoList(CommandCenterResponseVO[] commandCenterResponseVOS) {
        return Arrays.stream(commandCenterResponseVOS)
                .map(ReferenceService::makeCommandCenterDto)
                .collect(Collectors.toList());
    }

    private static CommandCenterDto makeCommandCenterDto(CommandCenterResponseVO commandCenterResponseVO) {
        return CommandCenterDto.builder()
                .commandCenterCode(commandCenterResponseVO.getCommandCenterCode())
                .colorCode(commandCenterResponseVO.getColorCode())
                .commandCenterDescription(commandCenterResponseVO.getCommandCenterDescription())
                .commandCenterId(commandCenterResponseVO.getCommandCenterId())
                .commandCenterLogo1(ImageUtility.convertToBase64image(commandCenterResponseVO.getCommandCenterLogo1()))
                .commandCenterLogo2(ImageUtility.convertToBase64image(commandCenterResponseVO.getCommandCenterLogo2()))
                .build();
    }

}
