package com.tpcmswebadmin.service.criminals.service.mapper;

import com.ssas.tpcms.engine.vo.response.CriminalsProfileResponseVO;
import com.tpcmswebadmin.service.criminals.domain.CasesDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CriminalProfileMapper {

    public static List<CasesDto> makeCasesDtoList(CriminalsProfileResponseVO[] criminalsProfileResponseVOS) {
        return Arrays.stream(criminalsProfileResponseVOS)
                .map(CriminalProfileMapper::makeCasesDto)
                .collect(Collectors.toList());
    }

    public static CasesDto makeCasesDto(CriminalsProfileResponseVO criminalsProfileResponseVO) {
        return CasesDto.builder()
                .nationalId(criminalsProfileResponseVO.getNationalIdNumber())
                .criminalName(criminalsProfileResponseVO.getFirstName_Ar() + " " + criminalsProfileResponseVO.getLastName_Ar())
                .address(criminalsProfileResponseVO.getContactAddress())
                .city(criminalsProfileResponseVO.getCityOfLiving())
                .state(null)
                .status(criminalsProfileResponseVO.getCrimianlStatusCode())
                .actions(prepareActionsColumn(criminalsProfileResponseVO.getCriminalsId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='/tpcmsWebAdmin/viewCriminal?criminalId={criminalId}' data-fancybox-card data-type='ajax' class='button button-v4 sml-icon-btn color-1' data-src='assets/ajax/card/officer-card.html'><i class='icon-view'></i></a>";

        return actionView.replace("{criminalId}", id);
    }

}
