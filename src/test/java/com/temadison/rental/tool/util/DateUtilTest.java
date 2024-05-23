package com.temadison.rental.tool.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilTest {

    /**
     * Note that in 2020, Independence Day (7/4) fell on a Saturday, so for the 2020 tests, Independence Day = 7/6/2020.
     * Note that in 2020, Labor Day (the first Monday in September [as identified in the assignment]) = 9/7/2020.
     */
    @Test
    void holidaysInDateRange() {
        assertEquals(0, DateUtil.holidaysInDateRange(LocalDate.of(2020, 1, 1), 31));
        assertEquals(0, DateUtil.holidaysInDateRange(LocalDate.of(2020, 1, 1), 180));
        assertEquals(1, DateUtil.holidaysInDateRange(LocalDate.of(2020, 1, 1), 210));
        assertEquals(1, DateUtil.holidaysInDateRange(LocalDate.of(2020, 1, 1), 240));
        assertEquals(2, DateUtil.holidaysInDateRange(LocalDate.of(2020, 1, 1), 270));
        assertEquals(0, DateUtil.holidaysInDateRange(LocalDate.of(2020, 7, 1), 4));
        assertEquals(1, DateUtil.holidaysInDateRange(LocalDate.of(2020, 7, 1), 5));
        assertEquals(0, DateUtil.holidaysInDateRange(LocalDate.of(2020, 9, 1), 5));
        assertEquals(1, DateUtil.holidaysInDateRange(LocalDate.of(2020, 9, 1), 6));
        assertEquals(1, DateUtil.holidaysInDateRange(LocalDate.of(2020, 7, 5), 63));
        assertEquals(0, DateUtil.holidaysInDateRange(LocalDate.of(2020, 7, 7), 61));
        assertEquals(1, DateUtil.holidaysInDateRange(LocalDate.of(2020, 7, 7), 62));
        assertEquals(2, DateUtil.holidaysInDateRange(LocalDate.of(2020, 7, 5), 64));
    }
}