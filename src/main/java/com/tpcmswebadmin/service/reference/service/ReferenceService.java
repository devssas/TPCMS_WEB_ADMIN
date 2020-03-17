package com.tpcmswebadmin.service.reference.service;

import com.ssas.tpcms.engine.vo.response.*;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseDataApiDto;
import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.service.reference.domain.dto.*;
import com.tpcmswebadmin.service.reference.domain.enums.ClientStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
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

    public List<CrimeTypesDto> getCrimeTypes() {
        try {
            return makeCrimeTypesDtoList(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getCrimeTypesMapping(null, null));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. ");
        }
        return Collections.emptyList();
    }

    public ResponseDataApiDto<CrimeTypesSimplifiedDto> getCrimeTypesSimplified() {
        try {
            return makeResponseDataApi(makeCrimeTypesDtoList(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getCrimeTypesMapping(null, null)));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. ");
        }

        return null;
    }

    private ResponseDataApiDto<CrimeTypesSimplifiedDto> makeResponseDataApi(List<CrimeTypesDto> crimeTypesList) {
        List<CrimeTypesSimplifiedDto> simplifiedDtoList = new ArrayList<>();

        for(CrimeTypesDto crimeTypes : crimeTypesList) {
            CrimeTypesSimplifiedDto crimeTypesSimplifiedDto = CrimeTypesSimplifiedDto.builder()
                    .id(crimeTypes.getCrimeTypeId())
                    .description(crimeTypes.getCrimeDescAr())
                    .title(crimeTypes.getCrimeNameAr())
                    .build();

            simplifiedDtoList.add(crimeTypesSimplifiedDto);
        }

        return new ResponseDataApiDto<>(simplifiedDtoList);
    }

    private List<CrimeTypesDto> makeCrimeTypesDtoList(CrimeTypeConfigResponseVO[] crimeTypeConfigResponseVOS) {
        return Arrays.stream(crimeTypeConfigResponseVOS)
                .map(ReferenceService::makeCrimeTypesDto)
                .collect(Collectors.toList());
    }

    private static CrimeTypesDto makeCrimeTypesDto(CrimeTypeConfigResponseVO crimeTypeConfigResponseVO) {
        return CrimeTypesDto.builder()
                .additionalRemarks(crimeTypeConfigResponseVO.getAdditionalRemarks())
                .crimeDescAr(crimeTypeConfigResponseVO.getCrimeDesc_Ar())
                .crimeNameAr(crimeTypeConfigResponseVO.getCrimeName_Ar())
                .crimeDescEn(crimeTypeConfigResponseVO.getCrimeDesc_En())
                .crimeNameEn(crimeTypeConfigResponseVO.getCrimeName_En())
                .crimeTypeCode(crimeTypeConfigResponseVO.getCrimeTypeCode())
                .crimeTypeId(crimeTypeConfigResponseVO.getCrimeTypeId())
                .crimeTypeLogo1(ImageUtility.convertToBase64image(crimeTypeConfigResponseVO.getCrimeTypeLogo1()))
                .crimeTypeLogo2(ImageUtility.convertToBase64image(crimeTypeConfigResponseVO.getCrimeTypeLogo2()))
                .build();
    }

    public List<AccessRoleDto> getAccessRoles() {
        try {
            return makeAccessRoleDtoList(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getAccessRoleMapping());
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. ");
        }

        return Collections.emptyList();
    }

    private List<AccessRoleDto> makeAccessRoleDtoList(AccessRolesConfigResponseVO[] accessRolesConfigResponseVOS) {
        return Arrays.stream(accessRolesConfigResponseVOS)
                .map(ReferenceService::makeAccessRoleDto)
                .collect(Collectors.toList());
    }

    private static AccessRoleDto makeAccessRoleDto(AccessRolesConfigResponseVO accessRolesConfigResponseVO) {
        return AccessRoleDto.builder()
                .accessRoleCode(accessRolesConfigResponseVO.getAccessRoleCode())
                .accessRoleId(accessRolesConfigResponseVO.getAccessRoleId())
                .roleDescription(accessRolesConfigResponseVO.getRoleDescription())
                .build();
    }

    public List<OfficerGradeDto> getOfficerGrades() {
        try {
            return makeOfficerGradeDtoList(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficerGradeMapping(null, null));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. ");
        }

        return Collections.emptyList();
    }

    private List<OfficerGradeDto> makeOfficerGradeDtoList(OfficerGradeConfigResponseVO[] officerGradeConfigResponseVOS) {
        return Arrays.stream(officerGradeConfigResponseVOS)
                .map(ReferenceService::makeOfficerGradeDto)
                .collect(Collectors.toList());
    }

    private static OfficerGradeDto makeOfficerGradeDto(OfficerGradeConfigResponseVO officerGradeConfigResponseVO) {
        return OfficerGradeDto.builder()
                .officerGradeId(officerGradeConfigResponseVO.getOfficerGradeId())
                .officerGradeCode(officerGradeConfigResponseVO.getOfficerGradeCode())
                .officerGradeDescAr(officerGradeConfigResponseVO.getOfficerGradeDesc_Ar())
                .officerGradeDescEn(officerGradeConfigResponseVO.getOfficerGradeDesc_En())
                .officerGradeNameAr(officerGradeConfigResponseVO.getOfficerGradeName_Ar())
                .officerGradeNameEn(officerGradeConfigResponseVO.getOfficerGradeName_en())
                .additionalRemarks(officerGradeConfigResponseVO.getAdditionalRemarks())
                .build();
    }
    
    
    public List<OfficerRankDto> getOfficersRank() {
        try {
            return makeOfficeRankDtoList(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficerRankMapping(null, null));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. ");
        }

        return Collections.emptyList();
    }

    private List<OfficerRankDto> makeOfficeRankDtoList(OfficerRankConfigResponseVO[] officerRankConfigResponseVOS) {
        return Arrays.stream(officerRankConfigResponseVOS)
                .map(ReferenceService::makeOfficerRankDto)
                .collect(Collectors.toList());
    }

    private static OfficerRankDto makeOfficerRankDto(OfficerRankConfigResponseVO officerRankConfigResponseVO) {
        return OfficerRankDto.builder()
                .officerRankId(officerRankConfigResponseVO.getOfficerRankId())
                .officerRankCode(officerRankConfigResponseVO.getOfficerRankCode())
                .officerRankDescAr(officerRankConfigResponseVO.getOfficerRankDesc_Ar())
                .officerRankDescEn(officerRankConfigResponseVO.getOfficerRankDesc_En())
                .officerRankNameAr(officerRankConfigResponseVO.getOfficerRankDesc_Ar())
                .officerRankNameEn(officerRankConfigResponseVO.getOfficerRankName_en())
                .additionalRemarks(officerRankConfigResponseVO.getAdditionalRemarks())
                .build();

    }

}
