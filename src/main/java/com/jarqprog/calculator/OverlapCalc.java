package com.jarqprog.calculator;

import com.jarqprog.exceptions.IncorrectCoordinatesException;

import java.math.BigInteger;

public interface OverlapCalc {

    BigInteger calculateOverlapArea(int[] firstRectangleCoordinates, int[] secondRectangleCoordinates)
            throws IncorrectCoordinatesException;
}
