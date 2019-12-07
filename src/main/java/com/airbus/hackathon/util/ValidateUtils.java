package com.airbus.hackathon.util;

import java.time.LocalDate;

public class ValidateUtils {

    public static boolean areDatesValid(String startDateStr, String endDateStr) {
        return areDatesValid(startDateStr, endDateStr, false);
    }

    public static boolean areDatesValid(LocalDate startDate, LocalDate endDate) {
        return areDatesValid(startDate, endDate, false);
    }

    public static boolean areDatesValid(String startDateStr, String endDateStr, boolean isEndDateExclusive) {
        if (startDateStr == null || endDateStr == null) {
            return false;
        }
        try {
            LocalDate startDate = DateUtil.toDate(startDateStr);
            LocalDate endDate = DateUtil.toDate(endDateStr);
            if (isEndDateExclusive) {
                endDate = endDate.minusDays(1);
            }
            if (endDate.isBefore(startDate)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean areDatesValid(LocalDate startDate, LocalDate endDate, boolean isEndDateExclusive) {
        if (startDate == null || endDate == null) {
            return false;
        }
        if (isEndDateExclusive) {
            endDate = endDate.minusDays(1);
        }
        if (endDate.isBefore(startDate)) {
            return false;
        }
        return true;
    }

}