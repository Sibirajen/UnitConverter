package com.sibirajen.unitConvBcknd.model.enums;

public enum Weight {
    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    OUNCE(28.3495),
    POUND(453.592);

    private final double val;

    Weight(double val) {
        this.val = val;
    }

    public double getVal() {
        return val;
    }
}
