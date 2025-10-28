package com.crud.tasks.stringtointconverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToIntConverter {

    private final ROUNDING roundingMode;

    public StringToIntConverter(ROUNDING roundingMode) {
        this.roundingMode = roundingMode;
    }

    public int convert(String number){
        //regexp pattern and idea taken from:
        // https://stackoverflow.com/questions/12643009/regular-expression-for-floating-point-numbers
        // https://www.w3schools.com/java/java_regex.asp

        Pattern pattern = Pattern.compile("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$");
        Matcher matcher = pattern.matcher(number);
        boolean isANumber = matcher.find();

        if(!isANumber) {
            throw new IllegalArgumentException();
        }

        double resultDoubleNumber = Double.parseDouble(number);
        return (int) (roundingMode.equals(ROUNDING.CEIL) ? Math.ceil(resultDoubleNumber) : Math.floor(resultDoubleNumber));
    };

}
