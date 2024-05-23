package com.temadison.rental.tool.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatUtil {

    private static final DateTimeFormatter LOCAL_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yy");
    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("$#,##0.00");
    private static final String PERCENT_FORMAT = "%d%%";


    public static String formatDate(LocalDate localDate) {
        return LOCAL_DATE_FORMAT.format(localDate);
    }

    public static String formatCurrency(BigDecimal bigDecimal) {
        return CURRENCY_FORMAT.format(bigDecimal);
    }

    public static String formatPercent(int integer) {
        return String.format(PERCENT_FORMAT, integer);
    }
}
