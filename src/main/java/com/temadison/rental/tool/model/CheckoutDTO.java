package com.temadison.rental.tool.model;

import java.time.LocalDate;
import java.util.Objects;

public class CheckoutDTO {

    private String toolCode;
    private Integer numberOfDays;
    private Integer discountPercent;
    private LocalDate checkoutDate;

    public CheckoutDTO(String toolCode, Integer numberOfDays, Integer discountPercent, LocalDate checkoutDate) {
        this.toolCode = toolCode;
        this.numberOfDays = numberOfDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }


    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
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
        CheckoutDTO that = (CheckoutDTO) object;
        return Objects.equals(toolCode, that.toolCode) &&
                Objects.equals(numberOfDays, that.numberOfDays) &&
                Objects.equals(discountPercent, that.discountPercent) &&
                Objects.equals(checkoutDate, that.checkoutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toolCode, numberOfDays, discountPercent, checkoutDate);
    }

    @Override
    public String toString() {
        return "CheckoutDTO{"
                + "toolCode='" + this.toolCode + "', "
                + "numberOfDays=" + this.numberOfDays + ", "
                + "discountPercent=" + this.discountPercent + ", "
                + "checkoutDate=" + this.checkoutDate
                + "}'";
    }
}
