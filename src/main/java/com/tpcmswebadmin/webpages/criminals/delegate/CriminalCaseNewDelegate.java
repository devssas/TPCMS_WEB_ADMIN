package com.tpcmswebadmin.webpages.criminals.delegate;

import com.tpcmswebadmin.webpages.criminals.model.CriminalCaseCreateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class CriminalCaseNewDelegate {

    public boolean createCriminalCase(CriminalCaseCreateModel criminalCaseCreateModel, HttpServletRequest request) {

        return true;
    }
}
