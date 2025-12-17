package br.com.douglasinformatica.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate date) {
        return date == null ? null : DATE_FORMATTER.format(date);
    }

    public static LocalDate parse(String date) {
        try {
            return DATE_FORMATTER.parse(date, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static Boolean isValidDate(String date) {
        return DateUtil.parse(date) != null;
    }
}