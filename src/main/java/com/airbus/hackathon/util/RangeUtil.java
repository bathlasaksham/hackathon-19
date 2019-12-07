package com.airbus.hackathon.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RangeUtil {

    /**
     * Returns a {@link List} of {@link LocalDate dates} for the range provided,
     * with inclusive start and exclusive end
     *
     * @param start - {@link LocalDate} - Inclusive
     * @param end   - {@link LocalDate} - Exclusive
     * @return {@link List} of {@link LocalDate}
     */
    public static List<LocalDate> between(LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<LocalDate>();
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            dates.add(date);
        }
        return dates;
    }

    /**
     * Returns a {@link List} of {@link LocalDate dates} for the range provided,
     * with both ends inclusive
     *
     * @param start - {@link LocalDate} - Inclusive
     * @param end   - {@link LocalDate} - Inclusive
     * @return {@link List} of {@link LocalDate}
     */
    public static List<LocalDate> betweenInclusive(LocalDate start, LocalDate end) {
        return between(start, end.plusDays(1));
    }

    public static List<String> betweenDates(String start, String end) {
        List<String> dates = new ArrayList<>();
        LocalDate startDate = DateUtil.toDate(start);
        LocalDate endDate = DateUtil.toDate(end);
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            dates.add(date.toString());
        }
        return dates;
    }

    public static List<LocalDate> between(String start, String end) {
        return between(DateUtil.toDate(start), DateUtil.toDate(end));
    }

    public static List<LocalDate> betweenInclusive(String start, String end) {
        return betweenInclusive(DateUtil.toDate(start), DateUtil.toDate(end));
    }

    public static Map<Integer, LocalDate> getDatesByIndexMap(LocalDate startDate, LocalDate endDate) {
        Map<Integer, LocalDate> reverseDateIndexMap = new HashMap<Integer, LocalDate>();
        int dateIdx = 0;
        for (LocalDate date : RangeUtil.betweenInclusive(startDate, endDate)) {
            reverseDateIndexMap.put(dateIdx, date);
            dateIdx++;
        }
        return reverseDateIndexMap;
    }

    public static Map<LocalDate, Integer> getIndexOfDatesMap(LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, Integer> indexOfDatesMap = new HashMap<LocalDate, Integer>();
        int dateIdx = 0;
        for (LocalDate date : RangeUtil.betweenInclusive(startDate, endDate)) {
            indexOfDatesMap.put(date, dateIdx);
            dateIdx++;
        }
        return indexOfDatesMap;
    }

    public static Map<LocalDate, String> dateToStringConversionMap(String startDateStr, String endDateStr, boolean inclusive) {
        LocalDate startDate = DateUtil.toDate(startDateStr);
        LocalDate endDate = DateUtil.toDate(endDateStr);
        if (!inclusive) {
            endDate = endDate.minusDays(1);
        }
        Map<LocalDate, String> dateToStringMap = new HashMap<LocalDate, String>();
        for (LocalDate dt : betweenInclusive(startDate, endDate)) {
            dateToStringMap.put(dt, DateUtil.toString(dt));
        }
        return dateToStringMap;
    }

    public static Map<LocalDate, String> dateToStringConversionMap(String startDateStr, String endDateStr) {
        return dateToStringConversionMap(startDateStr, endDateStr, false);
    }

    public static Map<String, LocalDate> stringToDateConversionMap(LocalDate startDate, LocalDate endDate, boolean inclusive) {
        Map<String, LocalDate> stringToDateMap = new HashMap<String, LocalDate>();
        if (!inclusive) {
            endDate = endDate.minusDays(1);
        }
        for (LocalDate dt : betweenInclusive(startDate, endDate)) {
            stringToDateMap.put(DateUtil.toString(dt), dt);
        }
        return stringToDateMap;
    }

    public static Map<String, LocalDate> stringToDateConversionMap(LocalDate startDate, LocalDate endDate) {
        return stringToDateConversionMap(startDate, endDate, false);
    }

}
