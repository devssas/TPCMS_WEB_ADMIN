package com.tpcmswebadmin.service.reference.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
public enum ClientStatus {

    ACT("ACT"),
    BLK("BLK"),
    TEMPBLK("TEMPBLK"),
    CR_WANTED("CR-WANTED"),
    CR_MOST_WANTED("CR-MOST-WANTED"),
    CR_ARRESTED("CR-ARRESTED"),
    CR_RELEASE("CR-RELEASE"),
    CR_PROFILE_OPEN("CR-PROFILE-OPEN"),
    CR_PROFILE_APPROVED("CR-PROFILE-APPROVED"),
    CR_PROFILE_REJECTED("CR-PROFILE-REJECTED"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    SUBMIT_FOR_CASE_REVIEW("SUBMIT_FOR_CASE_REVIEW"),
    REQUEST_FOR_EVIDENCE("REQUEST_FOR_EVIDENCE"),
    CLOSED("CLOSED"),
    PENDING("PENDING"),
    DELETED("DELETED"),
    DRAFT("DRAFT");

    @Getter
    private final String clientName;

}
