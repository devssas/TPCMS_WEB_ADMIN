package com.tpcmswebadmin.service.criminals.service.mapper;

import com.ssas.tpcms.engine.vo.response.CrimeReportResponseVO;
import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.service.criminals.domain.dto.CrimeReportDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CrimeReportsMapper {

    public static List<CrimeReportDto> makeCrimeReportDtoList(CrimeReportResponseVO[] crimeReportResponseVOS) {
        return Arrays.stream(crimeReportResponseVOS)
                .map(CrimeReportsMapper::makeCrimeReportDto)
                .collect(Collectors.toList());
    }

    public static CrimeReportDto makeCrimeReportDto(CrimeReportResponseVO crimeReportResponseVOS) {
        return CrimeReportDto.builder()
                .reportId(crimeReportResponseVOS.getCrimeReportsId())
                .officerName(crimeReportResponseVOS.getReportingFirstName_Ar() + " " + crimeReportResponseVOS.getReportingLastName_Ar())
                .address(crimeReportResponseVOS.getCrimeScene())
                .city(crimeReportResponseVOS.getCrimeLocation())
                .created(DateUtility.convertDateToString(DateUtility.convertFromStringToDate(crimeReportResponseVOS.getReportedDate())))
                .wantedBy("-")
                .status(crimeReportResponseVOS.getCrimianlStatusCode())
                .actions(prepareActionsColumn(crimeReportResponseVOS.getCrimeReportsId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String reportId = "{reportId}";

        String actionView = "<a href='javascript:;' data-fancybox-card data-type='ajax' class='button button-v4 sml-icon-btn color-1' data-src='/tpcmsWebAdmin/card/crime-report?crimeReportId={reportId}'><i class='icon-view'></i></a>";
        String actionUpdate = "<a href='/tpcmsWebAdmin/updateCrimeReport?reportId={reportId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-edit'></i></a>";

        // todo delete can be put for super admin

        return actionView.replace(reportId, id) + actionUpdate.replace(reportId, id);
    }

}
