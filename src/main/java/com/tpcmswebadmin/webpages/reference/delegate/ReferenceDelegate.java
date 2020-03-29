package com.tpcmswebadmin.webpages.reference.delegate;

import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.reference.domain.dto.*;
import com.tpcmswebadmin.service.reference.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReferenceDelegate {

    private final ReferenceService referenceService;

    public List<String> getClientStatus() {
        return referenceService.getClientStatus();
    }

    public List<String> getCrimeTypesName() {
        return referenceService.getCrimeTypes().stream()
                .map(CrimeTypesDto::getCrimeNameAr)
                .collect(Collectors.toList());
    }

    public List<String> getCrimeTypesTitle() {
        return referenceService.getCrimeTypesSimplified().getData().stream()
                .map(CrimeTypesSimplifiedDto::getTitle)
                .collect(Collectors.toList());
    }

    public List<String> getAllWeaponTypes() {
        return referenceService.getWeaponTypes();
    }

    public List<String> getOfficerUnitsForAdmin(HttpServletRequest httpServletRequest) {
        return Arrays.asList(httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT).toString());
    }

    public List<String> getOfficerUnits() {
        return referenceService.getOfficerUnitList().stream()
                .map(OfficerUnitDto::getUnitNumber)
                .collect(Collectors.toList());
    }

    public List<String> getNatureOfAnnouncement() {
        return referenceService.getNatureOfAnnouncement().stream()
                .map(NatureOfAnnouncementDto::getNatureOfAnnouncementDescription)
                .collect(Collectors.toList());
    }

    public List<String> getCommandCenterForAdmin(HttpServletRequest httpServletRequest) {
        return Arrays.asList(httpServletRequest.getSession().getAttribute(TpCmsConstants.COMMAND_CENTER).toString());
    }

    public List<String> getCommandCenter() {
        return referenceService.getCommandCenter().stream()
                .map(CommandCenterDto::getCommandCenterCode)
                .collect(Collectors.toList());
    }

    public List<String> getOfficerGrades() {
        return referenceService.getOfficerGrades().stream()
                .map(OfficerGradeDto::getOfficerGradeCode)
                .collect(Collectors.toList());
    }

    public List<String> getAccessRoles() {
        List<String> list = referenceService.getAccessRoles().stream()
                .map(AccessRoleDto::getAccessRoleCode)
                .collect(Collectors.toList());

        list.remove("SUPER-ADMIN");
        return list;
    }

    public List<String> getAccessRolesForAdmin() {
        return Collections.singletonList("OFFICER");
    }

    public List<String> getOfficersRanks() {
        return referenceService.getOfficersRank().stream()
                .map(OfficerRankDto::getOfficerRankCode)
                .collect(Collectors.toList());
    }

    public CommandCenterDto getCommandCenterId(String commandCenter) {
        return referenceService.getCommandCenter().stream().filter(entry -> entry.getCommandCenterDescription().equals(commandCenter)).findFirst().orElse(CommandCenterDto.builder().commandCenterCode("TP_HEAD_CC").commandCenterId("1").build());
    }
}
