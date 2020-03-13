package com.tpcmswebadmin.service.policeofficer.controller;

import com.tpcmswebadmin.infrastructure.domain.dto.ResponseSearchDataDto;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerSearchDto;
import com.tpcmswebadmin.service.policeofficer.domain.dto.SearchDto;
import com.tpcmswebadmin.service.policeofficer.service.PoliceOfficerSearchClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class PoliceOfficerSearchControllerAPI {

    private final PoliceOfficerSearchClientService policeOfficerSearchClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("policeStaff/search")
    public ResponseSearchDataDto<PoliceOfficerSearchDto> searchPoliceOfficers(@RequestParam(value = "firstNameAr", required = false) String firstNameAr,
                                                                              @RequestParam(value = "lastNameAr", required = false) String lastNameAr,
                                                                              @RequestParam(value = "criminalsCode", required = false) String criminalsCode,
                                                                              @RequestParam(value = "drivingLicenseNumber", required = false) String drivingLicenseNumber,
                                                                              @RequestParam(value = "passportNumber", required = false) String passportNumber,
                                                                              @RequestParam(value = "nationalID", required = false) String nationalID,
                                                                              HttpServletRequest httpServletRequest) {

        SearchDto searchDto = new SearchDto();

        if(firstNameAr != null)
            searchDto.setFirstName(firstNameAr);

        if(lastNameAr != null)
            searchDto.setLastName(lastNameAr);

        if(criminalsCode != null)
            searchDto.setCriminalsCode(criminalsCode);

        if(drivingLicenseNumber != null)
            searchDto.setDrivingLicenceNumber(drivingLicenseNumber);

        if(passportNumber != null)
            searchDto.setPassportNumber(passportNumber);

        if(nationalID != null)
            searchDto.setNationalId(nationalID);

        return policeOfficerSearchClientService.search(searchDto, httpServletRequest);
    }
}
