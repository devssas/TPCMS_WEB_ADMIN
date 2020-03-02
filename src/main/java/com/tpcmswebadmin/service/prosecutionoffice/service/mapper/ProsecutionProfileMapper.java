package com.tpcmswebadmin.service.prosecutionoffice.service.mapper;

import com.ssas.tpcms.engine.vo.response.CriminalsProfileResponseVO;
import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.service.criminals.domain.CasesDto;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionCasesDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProsecutionProfileMapper {

    public static List<ProsecutionCasesDto> makeProsecutionCasesDtoList(CriminalsProfileResponseVO[] criminalsProfileResponseVOS) {
        return Arrays.stream(criminalsProfileResponseVOS)
                .map(ProsecutionProfileMapper::makeProsecutionCasesDto)
                .collect(Collectors.toList());
    }

    public static ProsecutionCasesDto makeProsecutionCasesDto(CriminalsProfileResponseVO criminalsProfileResponseVO) {
        return ProsecutionCasesDto.builder()
                .caseId(criminalsProfileResponseVO.getCaseId())
                .caseDate(DateUtility.convertDateToString(DateUtility.convertFromStringToDate(criminalsProfileResponseVO.getArrestedDate())))
                .userId(criminalsProfileResponseVO.getCriminalsId())
                .location(criminalsProfileResponseVO.getCrimeLocation())
                .crimeType(criminalsProfileResponseVO.getCrimeClassification())
                .status(criminalsProfileResponseVO.getCrimianlStatusCode())
                .actions(prepareActionsColumn(criminalsProfileResponseVO.getCriminalsCode()))
                .criminalsCode(criminalsProfileResponseVO.getCriminalsCode())
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='/tpcmsWebAdmin/prosecutionManageCrimeFile?caseId={caseId}'  class='button button-v4 sml-icon-btn color-1'><i class='icon-view'></i></a>";

        return actionView.replace("{caseId}", id);
    }

}
