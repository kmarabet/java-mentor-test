package com.java_mentor;

public class NumberWithFormat {

    private int value;
    private NumberFormatType numberFormat;

    public NumberWithFormat(int value, NumberFormatType numberFormat) {
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
