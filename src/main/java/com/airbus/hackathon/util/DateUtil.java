package com.airbus.hackathon.util;


import com.airbus.hackathon.constant.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateUtil {

    /**
     * Returns {@link LocalDate currentDate} with default time zone
     *
     * @return {@link LocalDate}
     */
    public static LocalDate today() {
        return LocalDate.now(Constants.DEFAULT_ZONE_ID);
    }

    /**
     * Returns {@link LocalDateTime currentDateTime} with default time zone
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime now() {
        return LocalDateTime.now(Constants.DEFAULT_ZONE_ID);
    }

    public static LocalDateTime now(String localTimeZone) {
        if (localTimeZone == null) {
            return LocalDateTime.now(Constants.DEFAULT_ZONE_ID);
        }
        return LocalDateTime.now(ZoneId.of(localTimeZone));
    }

    public static LocalDateTime getDDayDate(String localTimeZone) {
        if (localTimeZone == null) {
            localTimeZone = Constants.TIME_ZONE_ASIA_KOLKATA;
        }
        LocalDateTime now = now(localTimeZone);
        if (now.getHour() < 6) {
            now = now.minusDays(1);
        }
        return now;
    }

    public static LocalDateTime toDateTime(String dateTimeString) {
        LocalDateTime dateTime = null;
        if (dateTimeString != null) {
            dateTime = LocalDateTime.parse(dateTimeString);
        }
        return dateTime;
    }

    /**
     * Returns max of the two dates
     *
     * @param d1 - {@link LocalDate}
     * @param d2 - {@link LocalDate}
     * @return {@link LocalDate}
     */
    public static LocalDate max(LocalDate d1, LocalDate d2) {
        return MathUtil.max(d1, d2);
    }

    /**
     * Returns min of the two dates
     *
     * @param d1 - {@link LocalDate}
     * @param d2 - {@link LocalDate}
     * @return {@link LocalDate}
     */
    public static LocalDate min(LocalDate d1, LocalDate d2) {
        return MathUtil.min(d1, d2);
    }

    public static LocalDateTime getDDayDate() {
        LocalDateTime now = now();
        if (now.getHour() < 6) {
            now = now.minusDays(1);
        }
        return now;
    }

    public static boolean isOverlapping(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2) {
        if (startDate1 == null || endDate1 == null || startDate2 == null || endDate2 == null) {
            return false;
        }
        return (!startDate1.isAfter(endDate2) && !endDate1.isBefore(startDate2));
    }

    /**
     * Returns number of days in between [d1, d2)
     *
     * @param d1 - {@link LocalDate} - Inclusive
     * @param d2 - {@link LocalDate} - Exclusive
     * @return {@link int} - number of days
     */
    public static int diffDays(LocalDate d1, LocalDate d2) {
        return (int) Math.abs(ChronoUnit.DAYS.between(d1, d2));
    }

    /**
     * Returns number of days in between [d1, d2]
     *
     * @param d1 - {@link LocalDate} - Inclusive
     * @param d2 - {@link LocalDate} - Inclusive
     * @return {@link int} - number of days
     */
    public static int totalDaysInclusive(LocalDate d1, LocalDate d2) {
        return diffDays(d1, d2) + 1;
    }

    /**
     * Converts String date to LocalDate
     *
     * @param dateStr - {@link String} - can be of format
     *                <strong>'d-M-yyyy'</strong> or <strong>'yyyy-M-d'</strong>
     * @return {@link LocalDate}
     */
    public static LocalDate toDate(String dateStr) {
        LocalDate date = null;
        if (dateStr != null) {
            dateStr = dateStr.replaceAll("/", "-");
            if (dateStr.matches("[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}")) {
                date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("d-M-yyyy"));
            } else {
                date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-M-d"));
            }
        }
        return date;
    }

    public static LocalDateTime toDateTimeFromIsoString(String dateTimeStr) {
        LocalDateTime localDateTime = null;
        if (dateTimeStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            localDateTime = LocalDateTime.parse(dateTimeStr.substring(0,23), formatter);
            localDateTime = localDateTime.plusHours(5).plusMinutes(30);
        }
        return localDateTime;
    }

    /**
     * Converts String date to LocalDate with default format
     *
     * @param date - {@link LocalDate}
     * @return {@link String} - formatted with default format as
     * <strong>'yyyy-MM-dd'</strong>
     */
    public static String toString(LocalDate date) {
        return toString(date, DateTimeFormatter.ISO_DATE);
    }

    /**
     * Converts String date to LocalDate with provided format
     *
     * @param date       - {@link LocalDate}
     * @param dateFormat - {@link String} - eg. <strong>'yyyy-MM-dd'</strong>
     * @return {@link String} - formatted in provided format
     */
    public static String toString(LocalDate date, String dateFormat) {
        return toString(date, DateTimeFormatter.ofPattern(dateFormat));
    }

    public static String toString(LocalDate date, DateTimeFormatter formatter) {
        String dateStr = null;
        if (formatter != null && date != null) {
            dateStr = formatter.format(date);
        }
        return dateStr;
    }

    public static String toString(LocalDateTime localDateTime) {
        return toString(localDateTime, "yyyy-MM-dd HH:mm");
    }

    public static String toString(LocalDateTime localDateTime, String dateTimeFormat) {
        return toString(localDateTime, DateTimeFormatter.ofPattern(dateTimeFormat));
    }

    public static String toString(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        String formattedDateTime = null;
        if (localDateTime != null) {
            ZonedDateTime zonedDateTime = localDateTime.atZone(Constants.DEFAULT_ZONE_ID);
            formattedDateTime = toString(zonedDateTime, formatter);
        }
        return formattedDateTime;
    }

    private static String toString(ZonedDateTime zonedDateTime, DateTimeFormatter formatter) {
        String formattedDateTime = null;
        if (formatter != null && zonedDateTime != null) {
            formattedDateTime = formatter.format(zonedDateTime);
        }
        return formattedDateTime;
    }

    /**
     * Returns the full day name of the {@link LocalDate date} provided. Eg.
     * <strong>Friday</strong>
     *
     * @param date - {@link LocalDate}
     * @return {@link String}
     */
    public static String dayName(LocalDate date) {
        if (date != null) {
            return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        }
        return null;
    }
}
