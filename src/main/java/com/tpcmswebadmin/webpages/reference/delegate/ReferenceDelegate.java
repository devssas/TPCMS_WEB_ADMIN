package com.tpcmswebadmin.webpages.reference.delegate;

import com.tpcmswebadmin.service.reference.domain.dto.CommandCenterDto;
import com.tpcmswebadmin.service.reference.domain.dto.NatureOfAnnouncementDto;
import com.tpcmswebadmin.service.reference.domain.dto.OfficerUnitDto;
import com.tpcmswebadmin.service.reference.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReferenceDelegate {

    private final ReferenceService referenceService;

    public List<String> getClientStatus() {
        return referenceService.getClientStatus();
    }

    public List<String> getAllWeaponTypes() {
        return referenceService.getWeaponTypes();
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

    public List<String> getCommandCenter() {
        return referenceService.getCommandCenter().stream()
                .map(CommandCenterDto::getCommandCenterCode)
                .collect(Collectors.toList());
    }
}
