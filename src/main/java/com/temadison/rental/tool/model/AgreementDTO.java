package com.temadison.rental.tool.model;

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
        return "Tool code: " + this.toolMO.getCode() + "\n"
                + "Tool type: " + this.toolMO.getType() + "\n"
                + "Tool brand: " + this.toolMO.getBrand() + "\n"
                + "Rental days: " + this.numberOfDays + "\n"
                + "Check out date: " + this.discountPercent + "\n"
                + "Due date: " + this.toolMO.getCode() + "\n"
                + "Daily rental charge: " + this.toolMO.getDailyRate() + "\n"
                + "Charge days: " + this.discountPercent + "\n"
                + "Pre-discount charge: " + this.toolMO.getCode() + "\n"
                + "Discount percent: " + this.numberOfDays + "\n"
                + "Discount amount: " + this.discountPercent + "\n"
                + "Final charge: " + this.checkoutDate;
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
