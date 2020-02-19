package com.tpcmswebadmin.infrastructure.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class DateUtility {

    public static String convertDateToString(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        if (date != null)
            return formatter.format(date);
        else return "";
    }

    public static Date convertFromStringToDate(String stringDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        Date date = new Date();

        if(stringDate == null)  {
            Date today = Date.from(Instant.now());
            stringDate = dateFormat.format(today);
        }
        
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            log.error("Error :", e);
        }
        return date;
    }

    public static String convertToFormat(String stringDate, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();

        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            log.error("Error :", e);
        }

        return dateFormat.format(date);
    }
}
