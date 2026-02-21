package com.study.goal.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataFormatter {
    private final String defaultStringFormat = "dd-MM-yyyy";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(defaultStringFormat);

    public LocalDate parse(String date){
        return LocalDate.parse(date, formatter);
    }
}
