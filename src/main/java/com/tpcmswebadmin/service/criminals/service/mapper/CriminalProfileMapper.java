package com.tpcmswebadmin.service.criminals.service.mapper;

import com.ssas.tpcms.engine.vo.response.CriminalsProfileResponseVO;
import com.tpcmswebadmin.service.criminals.domain.dto.CasesDto;
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
                .criminalsCode(criminalsProfileResponseVO.getCriminalsCode())
                .address(criminalsProfileResponseVO.getContactAddress())
                .city(criminalsProfileResponseVO.getCityOfLiving())
                .status(criminalsProfileResponseVO.getCrimianlStatusCode())
                .actions(prepareActionsColumn(criminalsProfileResponseVO.getCriminalsCode()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='javascript:;' data-fancybox-card data-type='ajax' class='button button-v4 sml-icon-btn color-1' data-src='/tpcmsWebAdmin/card/manageCases?criminalCode={criminalCode}'><i class='icon-view'></i></a>";

        return actionView.replace("{criminalCode}", id);
    }

}
