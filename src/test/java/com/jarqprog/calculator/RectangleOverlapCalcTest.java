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

        int[] firstRectangleNullCoordinates = new int[4];
        int[] secondRectangleNullCoordinates = new int[4];

        calc.calculateOverlapArea(firstRectangleNullCoordinates, secondRectangleNullCoordinates);
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
    public void testToString() {
    }
}