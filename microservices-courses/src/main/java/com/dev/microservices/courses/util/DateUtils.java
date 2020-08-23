package com.dev.microservices.courses.util;

import com.dev.microservices.courses.util.constants.Constants;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class DateUtils {

    public static final String dateToString(Date dateInput) {
        return Optional.ofNullable(dateInput)
                .map(date -> {
                    SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMATTER);
                    return formatter.format(date);
                })
                .orElse(null);
    }

    public static Date stringToDate(String dateInput) {
        return Optional.ofNullable(dateInput)
                .map(date -> {
                    SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMATTER);
                    try {
                        return formatter.parse(date);
                    } catch (ParseException e) {
                        log.info("Ocurrio un error al parsear stringToDate, {}", e);
                        return null;
                    }
                })
                .orElse(null);
    }
}