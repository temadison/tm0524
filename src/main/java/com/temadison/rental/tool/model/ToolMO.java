package com.temadison.rental.tool.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Tool")
public class ToolMO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_seq")
    @SequenceGenerator(name = "tool_seq", sequenceName = "tool_seq", allocationSize = 1)
    private Long id;
    private String code;

    private ToolType type;
    private Brand brand;

    private Double dailyRate;

    protected ToolMO() {
    }

    public ToolMO(String code, ToolType type, Brand brand, Double dailyRate) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.dailyRate = dailyRate;
    }

    public Long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public ToolType getType() {
        return this.type;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public Double getDailyRate() {
        return this.dailyRate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(ToolType type) {
        this.type = type;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setDailyRate(Double dailyRate) {
        this.dailyRate = dailyRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ToolMO))
            return false;
        ToolMO modelObject = (ToolMO) o;
        return Objects.equals(this.id, modelObject.id)
                && Objects.equals(this.code, modelObject.code)
                && Objects.equals(this.type, modelObject.type)
                && Objects.equals(this.brand, modelObject.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.code, this.type, this.brand);
    }

    @Override
    public String toString() {
        return "Tool{"
                + "id=" + this.id + ", "
                + "code='" + this.code + "', "
                + "type='" + this.type + "', "
                + "brand='" + this.brand + "', "
                + "dailyRate=" + this.dailyRate
                + "}'";
    }
}
