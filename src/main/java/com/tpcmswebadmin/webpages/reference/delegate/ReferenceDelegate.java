package com.tpcmswebadmin.webpages.reference.delegate;

import com.tpcmswebadmin.service.reference.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
