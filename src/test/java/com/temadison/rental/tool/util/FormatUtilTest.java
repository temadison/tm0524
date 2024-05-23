package com.temadison.rental.tool.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatUtilTest {

    @Test
    void formatDate() {
        assertEquals("07/04/20", FormatUtil.formatDate(LocalDate.of(2020, 7, 4)));
    }

    @Test
    void formatCurrency() {
        assertEquals("$1,234.56", FormatUtil.formatCurrency(new BigDecimal("1234.56")));
    }

    @Test
    void formatPercent() {
        assertEquals("5%", FormatUtil.formatPercent(5));
    }
}