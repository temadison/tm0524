package com.temadison.rental.tool.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class DateUtil {

    public static int holidaysInDateRange(LocalDate startDate, int numberOfDays) {
        int numHolidays = 0;
        LocalDate endDate = startDate.plusDays(numberOfDays);

        for (int year = startDate.getYear(); year <= endDate.getYear(); year++) {
            if (getIndependenceDay(year).isAfter(startDate) && !endDate.isBefore(getIndependenceDay(year))) {
                numHolidays++;
            }
            if (getLaborDay(year).isAfter(startDate) && !endDate.isBefore(getLaborDay(year))) {
                numHolidays++;
            }
        }
        return numHolidays;
    }

    private static LocalDate getIndependenceDay(int year) {
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        if (independenceDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            independenceDay = independenceDay.plusDays(2);
        } else if (independenceDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            independenceDay = independenceDay.plusDays(1);
        }
        return independenceDay;
    }

    private static LocalDate getLaborDay(int year) {
        return LocalDate.of(year, 9, 1).with(firstInMonth(DayOfWeek.MONDAY));
    }
}
