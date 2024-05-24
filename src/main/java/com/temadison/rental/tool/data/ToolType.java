package com.temadison.rental.tool.data;

import java.math.BigDecimal;

public enum ToolType {
    CHAINSAW("Chainsaw", new BigDecimal("1.49"), false, true),
    JACKHAMMER("Jackhammer", new BigDecimal("2.99"), false, false),
    LADDER("Ladder", new BigDecimal("1.99"), true, false);

    final String value;
    final BigDecimal dailyRate;
    final boolean includeWeekendDays;
    final boolean includeHolidays;

    ToolType(String value, BigDecimal dailyRate, boolean includeWeekendDays, boolean includeHolidays) {
        this.value = value;
        this.dailyRate = dailyRate;
        this.includeWeekendDays = includeWeekendDays;
        this.includeHolidays = includeHolidays;
    }

    String getValue() {
        return this.value;
    }

    public BigDecimal getDailyRate() {
        return this.dailyRate;
    }

    public boolean includeWeekendDays() {
        return includeWeekendDays;
    }

    public boolean includeHolidays() {
        return includeHolidays;
    }
}
