package com.jarqprog.calculator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class RectangleOverlapCalcTest {

    private OverlapCalc calc;

    @Before
    public void setUp() {
        calc = new RectangleOverlapCalc();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateUsingArgumentsThatMakeItImpossibleToBuildRectangles() {

        int[] firstIncorrectRectangleCoordinates = {1, 10, 0};
        int[] secondRectangleCoordinates = {1, 10, 20, 100};

        calc.calculateOverlapArea(firstIncorrectRectangleCoordinates, secondRectangleCoordinates);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateUsingNullValues() {

        calc.calculateOverlapArea(null, null);
    }

    @Test
    public void testCalculateUsingRegularParameters() {

        int[] firstRectangleCoordinates = {-1, -1, 10, 10};
        int[] secondRectangleCoordinates = {-1, 0, 3, 9};

        BigInteger result = calc.calculateOverlapArea(firstRectangleCoordinates, secondRectangleCoordinates);
        BigInteger expected = BigInteger.valueOf(36);

        assertTrue(result.equals(expected));

        int[] firstOtherRectangleCoordinates = {-12, -4, -1, -2};
        int[] secondOtherRectangleCoordinates = {0, 0, 100, 3000};

        result = calc.calculateOverlapArea(firstOtherRectangleCoordinates, secondOtherRectangleCoordinates);
        expected = BigInteger.valueOf(0);

        assertTrue(result.equals(expected));
    }

    @Test
    public void testCalculateUsingHugeNumbers() {

        int[] absurdlyHugeRectangleCoordinates = {-2147483648, -2147483648, 2147483647, 2147483647};

        BigInteger result = calc
                .calculateOverlapArea(absurdlyHugeRectangleCoordinates, absurdlyHugeRectangleCoordinates);

        BigInteger multiplier = BigInteger.valueOf(2147483647L + 2147483648L);
        BigInteger expected = multiplier.multiply(multiplier);

        assertTrue(result.equals(expected));
    }

    @Test
    public void testToStringWithDefaultFieldsValue() {

        String expected = "Not calculated yet or failed because of incorrect coordinates";

        assertEquals(expected, calc.toString());
    }


    // the tests below require calling the calculation method to change the states of the calc object:

    @Test
    public void testToStringAfterDoingNormalCalculations() {

        // 1. scenario

        String expected = "Overlap area for rectangles: [-10, -40, 10, 5] [-5, 0, 5, 5] = 50 squares";
        int[] firstRectangleCoordinates = {-10, -40, 10, 5};
        int[] secondRectangleCoordinates = {-5, 0, 5, 5};

        calc.calculateOverlapArea(firstRectangleCoordinates, secondRectangleCoordinates);

        assertEquals(expected, calc.toString());


        // 2. scenario

        expected = "Overlap area for rectangles: [-101, -40, 10, 50] [-500, 10, 1005, 105] = 4440 squares";
        int[] firstOtherRectangleCoordinates = {-101, -40, 10, 50};
        int[] secondOtherRectangleCoordinates = {-500, 10, 1005, 105};

        calc.calculateOverlapArea(firstOtherRectangleCoordinates, secondOtherRectangleCoordinates);

        assertEquals(expected, calc.toString());


        // 3. scenario

        expected = "Overlap area for rectangles: [-1, -1, 2, 2] [10, 10, 20, 20] = 0 squares";
        int[] firstOthersRectangleCoordinates = {-1, -1, 2, 2};
        int[] secondOthersRectangleCoordinates = {10, 10, 20, 20};

        calc.calculateOverlapArea(firstOthersRectangleCoordinates, secondOthersRectangleCoordinates);

        assertEquals(expected, calc.toString());
    }

    @Test
    public void testToStringAfterFailedCalculationCausedByHandledIllegalArgumentException() {

        // 1. scenario - null values

        final String expected = "Not calculated yet or failed because of incorrect coordinates";

        try {
            calc.calculateOverlapArea(null, null);
        } catch (IllegalArgumentException notUsed) {}  // swallow

        assertEquals(expected, calc.toString());


        // 2. scenario - coordinates with the incorrect number of elements

        int[] incorrectCoordinates = {-1, -1};
        int[] correctCoordinates = {10, 10, 20, 20};

        try {
            calc.calculateOverlapArea(incorrectCoordinates, correctCoordinates);
        } catch (IllegalArgumentException notUsed) {}  // swallow

        assertEquals(expected, calc.toString());
    }
}
