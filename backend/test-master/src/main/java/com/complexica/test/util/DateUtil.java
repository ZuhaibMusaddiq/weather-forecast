package com.complexica.test.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    /**
     * Add no of days to current date
     *
     * @param days
     * @return
     */
    public static LocalDate todayPlusDays(int days) {
        return new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(days);
    }

    /**
     * return true if time is between 12pm and 6pm else return false of given date
     *
     * @param inputDate
     * @param weatherDate
     * @return
     */
    public static boolean isTimeInDefaultRange(LocalDate inputDate, Date weatherDate) {
        LocalTime startTime = LocalTime.parse("12:00:00");
        LocalTime endTime = LocalTime.parse("18:00:00");
        LocalTime localeTime = convertDateToLocalTime(weatherDate);
        LocalDate localDate = convertDateToLocalDate(weatherDate);
        return localeTime.isAfter(startTime) && localeTime.isBefore(endTime) && inputDate.isEqual(localDate);
    }

    /**
     * Convert java.util.Date to java.time.LocalTime
     *
     * @param dateToConvert
     * @return
     */
    public static LocalTime convertDateToLocalTime(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalTime();
    }

    public static LocalDate convertDateToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
