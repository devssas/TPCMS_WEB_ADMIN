package com.tpcmswebadmin.service.reference.controller;

import com.tpcmswebadmin.service.reference.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reference")
public class ReferenceController {

    private final ReferenceService referenceService;

    @GetMapping("status")
    public List<String> getClientStatus() {
        return referenceService.getClientStatus();
    }

    @GetMapping("weapon-types")
    public List<String> getWeaponTypes() {
        return referenceService.getWeaponTypes();
    }
}
