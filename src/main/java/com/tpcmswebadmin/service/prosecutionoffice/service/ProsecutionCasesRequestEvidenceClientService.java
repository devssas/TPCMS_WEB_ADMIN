package com.tpcmswebadmin.service.prosecutionoffice.service;

import com.ssas.tpcms.engine.vo.request.ViewCriminalProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionCasesDto;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionRequestEvidenceDetailDto;
import com.tpcmswebadmin.service.prosecutionoffice.service.mapper.ProsecutionProfileMapper;
import com.tpcmswebadmin.service.reference.domain.ClientStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProsecutionCasesRequestEvidenceClientService implements ClientServiceAPI<ProsecutionCasesDto, LoginUserDo, ViewCriminalProfileRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public ProsecutionRequestEvidenceDetailDto getProsecutionRequestEvidenceDetailDto(String criminalsCode, HttpServletRequest httpServletRequest) {
        ViewCriminalProfileRequestVO viewCriminalProfileRequestVO = new ViewCriminalProfileRequestVO();
        viewCriminalProfileRequestVO.setLoginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE));
        viewCriminalProfileRequestVO.setCriminalsCode(criminalsCode);
        viewCriminalProfileRequestVO.setStatusCode(ClientStatus.REQUEST_FOR_EVIDENCE.getClientName());
        viewCriminalProfileRequestVO.setMobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID));
        setCredentials(viewCriminalProfileRequestVO);

        try {
            log.info("criminal reports request will be sent to client. {}", viewCriminalProfileRequestVO.getMobileAppUserName());

            return prepareProsecutionRequestEvidenceDetailDto(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getCriminalsProfile(viewCriminalProfileRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on criminal reports request. " + viewCriminalProfileRequestVO.getMobileAppUserName());
        }
        return null;
    }

    private ProsecutionRequestEvidenceDetailDto prepareProsecutionRequestEvidenceDetailDto(TPEngineResponse criminalsProfile) {
        return ProsecutionRequestEvidenceDetailDto.builder()
                .caseId(criminalsProfile.getCriminalProfileList()[0].getCaseId())
                .caseDate(criminalsProfile.getCriminalProfileList()[0].getFlagedDate())
                .officerId(criminalsProfile.getCriminalProfileList()[0].getArrestedOfficerId())
                .crimeLocation(criminalsProfile.getCriminalProfileList()[0].getCrimeLocation())
                .status(criminalsProfile.getCriminalProfileList()[0].getCrimianlStatusCode())
                .suspects(Collections.singletonList(StringUtility.makeFullName(criminalsProfile.getCriminalProfileList()[0].getFirstName_Ar() + " " + criminalsProfile.getCriminalProfileList()[0].getLastName_Ar())))
                .images(Arrays.asList(ImageUtility.convertToBase64image(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto1()),
                                      ImageUtility.convertToBase64image(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto2()),
                                      ImageUtility.convertToBase64image(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto3()),
                                      ImageUtility.convertToBase64image(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto4()),
                                      ImageUtility.convertToBase64image(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto5()),
                                      ImageUtility.convertToBase64image(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto6()),
                                      ImageUtility.convertToBase64image(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto7()),
                                      ImageUtility.convertToBase64image(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto8()),
                                      ImageUtility.convertToBase64image(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto9()),
                                      ImageUtility.convertToBase64image(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto10())))
                .crimeType(criminalsProfile.getCriminalProfileList()[0].getTypeOfCrime())
                .crimeTitle(criminalsProfile.getCriminalProfileList()[0].getCrimeName())
                .crimeClassification(criminalsProfile.getCriminalProfileList()[0].getCrimeClassification())
                .witnessFirstName(criminalsProfile.getCriminalProfileList()[0].getFirstName_Ar())
                .witnessLastName(criminalsProfile.getCriminalProfileList()[0].getLastName_Ar())
                .witnessStatement(criminalsProfile.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getAdminOfficerStatement())
                .build();
    }

    @Override
    public ResponseDto<ProsecutionCasesDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(ProsecutionProfileMapper.makeProsecutionCasesDtoList(response.getCriminalProfileList()));
    }

    @Override
    public ResponseDto<ProsecutionCasesDto> prepareResponseDto(List<ProsecutionCasesDto> list) {
        ResponseDto<ProsecutionCasesDto> responseDto = new ResponseDto<>();
        DataDto<ProsecutionCasesDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseDto.setData(dataDto);
        responseDto.setMessage("status");
        responseDto.setStatus("true");

        return responseDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewCriminalProfileRequestVO viewCriminalProfileRequestVO = new ViewCriminalProfileRequestVO();
        viewCriminalProfileRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewCriminalProfileRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewCriminalProfileRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewCriminalProfileRequestVO.setCriminalsProfileSeeAll("Y");
        viewCriminalProfileRequestVO.setStatusCode(ClientStatus.REQUEST_FOR_EVIDENCE.getClientName());

        viewCriminalProfileRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        setCredentials(viewCriminalProfileRequestVO);

        try {
            log.info("criminal reports request will be sent to client. {}", viewCriminalProfileRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getCriminalsProfile(viewCriminalProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on criminal reports request. " + viewCriminalProfileRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public void setCredentials(ViewCriminalProfileRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Case ID");
        list.add("Case Date");
        list.add("User Id");
        list.add("Location");
        list.add("Crime Type");
        list.add("Status");
        list.add("Actions");

        return list;
    }
}
