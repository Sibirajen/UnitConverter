package com.sibirajen.unitConvBcknd.model;

import com.sibirajen.unitConvBcknd.model.enums.Type;

public class Request {
    private Type type;
    private double value;
    private String fromUnit;
    private String toUnit;

    public Request() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Request{" +
                "type='" + type + '\'' +
                ", value=" + value +
                ", fromUnit='" + fromUnit + '\'' +
                ", toUnit='" + toUnit + '\'' +
                '}';
    }
}
