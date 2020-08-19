package com.dev.microservices.core.users.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class DateUtils {

    public static String dateToString(Date inputDate) {
        return Optional.ofNullable(inputDate)
                .map(date -> {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    return formatter.format(date);
                })
                .orElse(null);
    }

    public static Date stringToDate(String dateInput) {
        return Optional.ofNullable(dateInput)
                .map(date -> {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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