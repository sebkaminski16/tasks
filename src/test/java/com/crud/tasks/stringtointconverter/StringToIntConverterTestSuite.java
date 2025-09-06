package com.crud.tasks.stringtointconverter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringToIntConverterTestSuite {

    @Test
    public void testShouldConvertInt() {
        //arrange
        StringToIntConverter stringToIntConverter = new StringToIntConverter(ROUNDING.FLOOR);
        String number = "2025";
        //act
        int convertedNumber = stringToIntConverter.convert(number);
        //assert
        Assertions.assertEquals(2025, convertedNumber);
    }

    @Test
    public void testShouldConvertDoubleAndRoundToTop() {
        //arrange
        StringToIntConverter stringToIntConverter = new StringToIntConverter(ROUNDING.CEIL);
        String number = "3.14";
        //act
        int convertedNumber = stringToIntConverter.convert(number);
        //assert
        Assertions.assertEquals(4, convertedNumber);
    }

    @Test
    public void testShouldConvertDoubleAndRoundToBottom() {
        //arrange
        StringToIntConverter stringToIntConverter = new StringToIntConverter(ROUNDING.FLOOR);
        String number = "3.14";
        //act
        int convertedNumber = stringToIntConverter.convert(number);
        //assert
        Assertions.assertEquals(3, convertedNumber);
    }

    @Test
    public void testShouldThrowExceptionWhenArgumentIsNotANumber() {
        //arrange
        StringToIntConverter stringToIntConverter = new StringToIntConverter(ROUNDING.CEIL);
        String number = "Not a number";
        //act
        //assert
        Assertions.assertThrows(NotANumberException.class, () -> stringToIntConverter.convert(number));
    }

}
