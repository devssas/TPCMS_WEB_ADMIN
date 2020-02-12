package com.tpcmswebadmin.webpages.authentication.domain;

public class SignInPassCodeDto {

    private boolean hasResult;

    private String officerCode;

    private String reportUnit;

    public SignInPassCodeDto() {
    }

    public SignInPassCodeDto(boolean hasResult, String officerCode, String reportUnit) {
        this.hasResult = hasResult;
        this.officerCode = officerCode;
        this.reportUnit = reportUnit;
    }

    public boolean isHasResult() {
        return hasResult;
    }

    public void setHasResult(boolean hasResult) {
        this.hasResult = hasResult;
    }

    public String getOfficerCode() {
        return officerCode;
    }

    public void setOfficerCode(String officerCode) {
        this.officerCode = officerCode;
    }

    public String getReportUnit() {
        return reportUnit;
    }

    public void setReportUnit(String reportUnit) {
        this.reportUnit = reportUnit;
    }

}
