package com.temadison.rental.tool.model;

import com.temadison.rental.tool.util.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class AgreementDTO {

    private ToolMO toolMO;
    private Integer numberOfDays;
    private Integer discountPercent;
    private LocalDate checkoutDate;

    public AgreementDTO(ToolMO toolMO, Integer numberOfDays, Integer discountPercent, LocalDate checkoutDate) {
        this.toolMO = toolMO;
        this.numberOfDays = numberOfDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }


    public ToolMO getTool() {
        return toolMO;
    }

    public void setToolCode(ToolMO toolMO) {
        this.toolMO = toolMO;
    }


    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        AgreementDTO that = (AgreementDTO) object;
        return Objects.equals(toolMO.getCode(), that.toolMO.getCode()) &&
                Objects.equals(numberOfDays, that.numberOfDays) &&
                Objects.equals(discountPercent, that.discountPercent) &&
                Objects.equals(checkoutDate, that.checkoutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toolMO.getCode(), numberOfDays, discountPercent, checkoutDate);
    }

    public String asReport() {
        Integer chargeDays = this.calculateChargeDays();
        BigDecimal discountAmount = this.calculateDiscountAmount();
        return "Tool code: " + this.toolMO.getCode() + "\n"
                + "Tool type: " + this.toolMO.getType().getValue() + "\n"
                + "Tool brand: " + this.toolMO.getBrand().getValue() + "\n"
                + "Rental days: " + this.numberOfDays + "\n"
                + "Check out date: " + this.checkoutDate + "\n"
                + "Due date: " + this.checkoutDate.plusDays(this.numberOfDays) + "\n"
                + "Daily rental charge: " + this.toolMO.getDailyRate() + "\n"
                + "Charge days: " + chargeDays + "\n"
                + "Pre-discount charge: " + this.toolMO.getDailyRate().multiply(new BigDecimal(chargeDays.toString())) + "\n"
                + "Discount percent: " + this.discountPercent + "\n"
                + "Discount amount: " + discountAmount + "\n"
                + "Final charge: " + this.toolMO.getDailyRate().multiply(new BigDecimal(chargeDays)).subtract(discountAmount);
    }

    private Integer calculateChargeDays() {
        return this.numberOfDays - DateUtil.holidaysInDateRange(this.checkoutDate, this.numberOfDays);
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
                + "}'";
    }
}
