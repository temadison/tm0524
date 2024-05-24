package com.temadison.rental.tool.data;

import com.temadison.rental.tool.data.model.ToolMO;
import com.temadison.rental.tool.util.DateUtil;
import com.temadison.rental.tool.util.FormatUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class Agreement {

    private ToolMO toolMO;
    private Integer numberOfDays;
    private Integer discountPercent;
    private LocalDate checkoutDate;

    public Agreement(ToolMO toolMO, Integer numberOfDays, Integer discountPercent, LocalDate checkoutDate) {
        this.toolMO = toolMO;
        this.numberOfDays = numberOfDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }


    public ToolMO getTool() {
        return toolMO;
    }


    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Agreement that = (Agreement) object;
        return Objects.equals(toolMO.getCode(), that.toolMO.getCode()) &&
                Objects.equals(numberOfDays, that.numberOfDays) &&
                Objects.equals(discountPercent, that.discountPercent) &&
                Objects.equals(checkoutDate, that.checkoutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toolMO.getCode(), numberOfDays, discountPercent, checkoutDate);
    }

    /**
     * Produce a formatted Rental Agreement.
     *
     * @return
     */
    public String asReport() {
        Integer chargeDays = this.calculateChargeDays();
        BigDecimal discountAmount = this.calculateDiscountAmount();
        return "Tool code: " + this.toolMO.getCode() + "\n"
                + "Tool type: " + this.toolMO.getType().getValue() + "\n"
                + "Tool brand: " + this.toolMO.getBrand().getValue() + "\n"
                + "Rental days: " + this.numberOfDays + "\n"
                + "Check out date: " + FormatUtil.formatDate(this.checkoutDate) + "\n"
                + "Due date: " + FormatUtil.formatDate(this.checkoutDate.plusDays(this.numberOfDays)) + "\n"
                + "Daily rental charge: " + FormatUtil.formatCurrency(this.toolMO.getDailyRate()) + "\n"
                + "Charge days: " + chargeDays + "\n"
                + "Pre-discount charge: " + FormatUtil.formatCurrency(this.toolMO.getDailyRate().multiply(new BigDecimal(chargeDays.toString()))) + "\n"
                + "Discount percent: " + FormatUtil.formatPercent(this.discountPercent) + "\n"
                + "Discount amount: " + FormatUtil.formatCurrency(discountAmount) + "\n"
                + "Final charge: " + FormatUtil.formatCurrency(this.toolMO.getDailyRate().multiply(new BigDecimal(chargeDays)).subtract(discountAmount));
    }

    private Integer calculateChargeDays() {
        Integer chargeDays = this.numberOfDays;
        if (!this.toolMO.includeHolidays()) {
            chargeDays -= DateUtil.holidaysInDateRange(this.checkoutDate, this.numberOfDays);
        }
        if (!this.toolMO.includeWeekendDays()) {
            chargeDays -= DateUtil.weekendDaysInDateRange(this.checkoutDate, this.numberOfDays);
        }
        return chargeDays;
    }

    private BigDecimal calculateDiscountAmount() {
        BigDecimal preDiscountCharge = this.toolMO.getDailyRate().multiply(new BigDecimal(this.calculateChargeDays()));
        BigDecimal listPercent = new BigDecimal(this.discountPercent.toString());
        return preDiscountCharge.multiply(listPercent).divide(new BigDecimal("100"), RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "AgreementDTO{"
                + "toolMO=" + this.toolMO + ", "
                + "numberOfDays=" + this.numberOfDays + ", "
                + "discountPercent=" + this.discountPercent + ", "
                + "checkoutDate=" + this.checkoutDate
                + "}";
    }
}
