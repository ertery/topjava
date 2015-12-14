package ru.javawebinar.topjava.util;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static final DateTimeFormatter DATE_TME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenTime(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetweenDay(LocalDate ld, LocalDate startDay, LocalDate endDay) {
        return ld.compareTo(startDay) >= 0 && ld.compareTo(endDay) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TME_FORMATTER);
    }

    public static LocalDate fromDate(String fromDate){
        return isValid(fromDate) ? LocalDate.parse(fromDate) : LocalDate.MIN;
    }
    public static LocalDate toDate(String toDate){
        return isValid(toDate)? LocalDate.parse(toDate) : LocalDate.MAX ;
    }
    public static LocalTime fromTime(String fromTime){
        return isValid(fromTime)? LocalTime.parse(fromTime) : LocalTime.MIN;
    }

    public static LocalTime toTime(String toTime){
        return isValid(toTime)? LocalTime.parse(toTime) : LocalTime.MAX;
    }
    private static boolean isValid(String s){
        return (s != null && !s.isEmpty());
    }

}
