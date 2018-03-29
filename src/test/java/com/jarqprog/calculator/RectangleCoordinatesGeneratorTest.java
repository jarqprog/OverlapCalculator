package com.jarqprog.calculator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RectangleCoordinatesGeneratorTest {

    private ShapeCoordinatesGenerator generator;

    @Before
    public void setUp() {

        int maxCoordinateValue = 10;
        generator = new RectangleCoordinatesGenerator(maxCoordinateValue);
    }

    @Test(expected=InstantiationError.class)
    public void testConstructorUsingArgumentWithTooLowValue() {

        generator = new RectangleCoordinatesGenerator(0);
    }

    @Test(expected=InstantiationError.class)
    public void testConstructorUsingArgumentWithTooHighValue() {

        new RectangleCoordinatesGenerator(99999999);
    }


    @Test
    public void testIfCoordinatesArraysHaveProperLength() {

        int properLength = 4;

        int[] firstRectangle = generator.generateCoordinates();
        int[] secondRectangle = generator.generateCoordinates();
        int[] thirdRectangle = generator.generateCoordinates();

        assertEquals(properLength, firstRectangle.length);
        assertEquals(properLength, secondRectangle.length);
        assertEquals(properLength, thirdRectangle.length);
    }

    @Test
    public void testIfCoordinatesArraysHaveProperValues() {

        int X_BOTTOM_INDEX = 0;
        int Y_BOTTOM_INDEX = 1;
        int X_TOP_INDEX = 2;
        int Y_TOP_INDEX = 3;

        int MAXIMUM_CORRECT_VALUE = generator.getMaxCoordinateValue();
        int MINIMUM_CORRECT_VALUE = MAXIMUM_CORRECT_VALUE * -1;

        int[] firstRectangle = generator.generateCoordinates();
        int[] secondRectangle = generator.generateCoordinates();
        int[] thirdRectangle = generator.generateCoordinates();

        List<int[]> coordinatesCollection = new ArrayList<>(Arrays
                .asList(firstRectangle, secondRectangle, thirdRectangle));


        boolean isTopValueBiggerThanBottomValue = coordinatesCollection.stream()
                .anyMatch(array -> array[X_TOP_INDEX] > array[X_BOTTOM_INDEX] ||
                        array[Y_TOP_INDEX] > array[Y_BOTTOM_INDEX]);

        assertTrue(isTopValueBiggerThanBottomValue);


        boolean isValueInProperRange = coordinatesCollection.stream()
                .allMatch(array -> Arrays.stream(array)
                .allMatch(num -> num >= MINIMUM_CORRECT_VALUE && num <= MAXIMUM_CORRECT_VALUE));

        assertTrue(isValueInProperRange);
    }
}
