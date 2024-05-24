package com.temadison.rental.tool.data.model;

import com.temadison.rental.tool.data.Brand;
import com.temadison.rental.tool.data.ToolType;
import jakarta.persistence.*;

import java.math.BigDecimal;
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

    protected ToolMO() {
    }

    public ToolMO(String code, ToolType type, Brand brand) {
        this.code = code;
        this.type = type;
        this.brand = brand;
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

    public BigDecimal getDailyRate() {
        return this.type.getDailyRate();
    }

    public boolean includeWeekendDays() {
        return type.includeWeekendDays();
    }

    public boolean includeHolidays() {
        return type.includeHolidays();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof ToolMO))
            return false;
        ToolMO that = (ToolMO) object;
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.code, that.code)
                && Objects.equals(this.type, that.type)
                && Objects.equals(this.brand, that.brand);
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
                + "brand='" + this.brand
                + "}";
    }
}
