package com.temadison.rental.tool.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class DateUtil {

    /**
     * Returns the number of holidays in the date range (excludes start date, includes end date).
     *
     * @param startDate
     * @param numberOfDays
     * @return number of holidays in the date range
     */
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

    /**
     * Returns the number of weekend days in the date range (excludes start date, includes end date).
     *
     * @param checkoutDate
     * @param numberOfDays
     * @return number of weekend days in the date range
     */
    public static Integer weekendDaysInDateRange(LocalDate checkoutDate, Integer numberOfDays) {
        int numWeekendDays = Math.floorDiv(numberOfDays, 7) * 2;
        int remainderDays = numberOfDays - Math.floorDiv(numberOfDays, 7) * 7;

        if (remainderDays > 0) {
            switch(checkoutDate.getDayOfWeek()) {
                case SUNDAY:
                    numWeekendDays += remainderDays == 6 ? 1 : 0;
                    break;
                case MONDAY:
                    numWeekendDays += remainderDays == 5 ? 1 : (remainderDays == 6 ? 2 : 0);
                    break;
                case TUESDAY:
                    numWeekendDays += remainderDays == 4 ? 1 : (((remainderDays == 5) || (remainderDays == 6)) ? 2 : 0);
                    break;
                case WEDNESDAY:
                    numWeekendDays += remainderDays == 3 ? 1 : (((remainderDays == 1) || (remainderDays == 2)) ? 0 : 2);
                    break;
                case THURSDAY:
                    numWeekendDays += remainderDays == 2 ? 1 : (remainderDays == 1 ? 0 : 2);
                    break;
                case FRIDAY:
                    numWeekendDays += remainderDays == 1 ? 1 : 2;
                    break;
                case SATURDAY:
                    numWeekendDays += 1;
                    break;
            }
        }

        return numWeekendDays;
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
