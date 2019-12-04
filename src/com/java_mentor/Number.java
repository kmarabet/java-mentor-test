package com.java_mentor;

public class Number {

    private int value;
    private NumberFormatType numberFormat;

    public Number(int value, NumberFormatType numberFormat) {
        this.value = value;
        this.numberFormat = numberFormat;
    }

    public int getValue() {
        return value;
    }
    public NumberFormatType getNumberFormat() {
        return numberFormat;
    }
}
