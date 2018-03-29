package com.jarqprog.calculator;

import com.jarqprog.exceptions.IncorrectCoordinatesException;

import java.math.BigInteger;
import java.util.Arrays;

public class RectangleOverlapCalc implements OverlapCalc {

    private final int X_BOTTOM_INDEX = 0;
    private final int Y_BOTTOM_INDEX = 1;
    private final int X_TOP_INDEX = 2;
    private final int Y_TOP_INDEX = 3;

    private int[] firstRectangleCoordinates;
    private int[] secondRectangleCoordinates;

    private BigInteger result;

    /*
    Rectangles are made from coordinates:
    [x, y, x', y'] where x,y define bottom corner
    and x',y' define top rectangle's corner
    on the coordinate axis.
    */

    public RectangleOverlapCalc() {
        firstRectangleCoordinates = new int[0];
        secondRectangleCoordinates = new int[0];
        result = BigInteger.valueOf(0);
    }

    @Override
    public BigInteger calculateOverlapArea(int[] firstRectangleCoordinates, int[] secondRectangleCoordinates)
            throws IncorrectCoordinatesException {
        // I used two int arrays for better readability of the code and (by the way) it forces the use of a int numbers
        // I have declared BigInteger as a return type (result) to be able to operate on a large rectangle,
        // e.g. shape constructed using coordinates: {-2147483648, -2147483648, 2147483647, 2147483647}

        result = BigInteger.valueOf(0);
        this.firstRectangleCoordinates = firstRectangleCoordinates;
        this.secondRectangleCoordinates = secondRectangleCoordinates;

        validateArguments();  // throws IncorrectCoordinatesException

        if ( haveRectanglesCommonArea() ) {
            result = calculate();
        }
        return result;
    }

    private void validateArguments() throws IncorrectCoordinatesException {
        String message = "Coordinates should contain four integer numbers x,y (left bottom corner)," +
                " x',y' (right top corner), eg. [-4, 3, 20, 22]";
        if(
                (firstRectangleCoordinates == null || secondRectangleCoordinates == null) ||
                (firstRectangleCoordinates.length != 4 || secondRectangleCoordinates.length != 4)
        ) {
            throw new IncorrectCoordinatesException(message);
        }
    }

    private boolean haveRectanglesCommonArea() {

        boolean hasFirstRectangleCorrectCoordinates =
                firstRectangleCoordinates[X_BOTTOM_INDEX] < firstRectangleCoordinates[X_TOP_INDEX] &&
                firstRectangleCoordinates[Y_BOTTOM_INDEX] < firstRectangleCoordinates[Y_TOP_INDEX];
        boolean hasSecondRectangleCorrectCoordinates =
                secondRectangleCoordinates[X_BOTTOM_INDEX] < secondRectangleCoordinates[X_TOP_INDEX] &&
                secondRectangleCoordinates[Y_BOTTOM_INDEX] < secondRectangleCoordinates[Y_TOP_INDEX];
        boolean whetherFirstOverlapConditionHasBeenMet =
                secondRectangleCoordinates[X_BOTTOM_INDEX] < firstRectangleCoordinates[X_TOP_INDEX] &&
                secondRectangleCoordinates[Y_BOTTOM_INDEX] < firstRectangleCoordinates[Y_TOP_INDEX];
        boolean whetherSecondOverlapConditionHasBeenMet =
                secondRectangleCoordinates[X_TOP_INDEX] > firstRectangleCoordinates[X_BOTTOM_INDEX] &&
                secondRectangleCoordinates[Y_TOP_INDEX] > firstRectangleCoordinates[Y_BOTTOM_INDEX];

        return hasFirstRectangleCorrectCoordinates && hasSecondRectangleCorrectCoordinates &&
                whetherFirstOverlapConditionHasBeenMet && whetherSecondOverlapConditionHasBeenMet;
    }

    private BigInteger calculate() {

        long bottomX, bottomY, topX, topY;

        bottomX = getBiggerNumber(firstRectangleCoordinates[X_BOTTOM_INDEX], secondRectangleCoordinates[X_BOTTOM_INDEX]);
        bottomY = getBiggerNumber(firstRectangleCoordinates[Y_BOTTOM_INDEX], secondRectangleCoordinates[Y_BOTTOM_INDEX]);
        topX = getSmallerNumber(firstRectangleCoordinates[X_TOP_INDEX], secondRectangleCoordinates[X_TOP_INDEX]);
        topY = getSmallerNumber(firstRectangleCoordinates[Y_TOP_INDEX], secondRectangleCoordinates[Y_TOP_INDEX]);

        return BigInteger.valueOf(topX - bottomX).multiply(BigInteger.valueOf(topY - bottomY));
    }

    private long getBiggerNumber(long firstNum, long secondNum) {
        if(firstNum > secondNum) {
            return firstNum;
        }
        return secondNum;
    }

    private long getSmallerNumber(long firstNum, long secondNum) {
        if(firstNum < secondNum) {
            return firstNum;
        }
        return secondNum;
    }

    @Override
    public String toString() {
        try {
            validateArguments();
        } catch (IncorrectCoordinatesException notUsed) {
            return "Not calculated yet or failed because of incorrect coordinates";
        }
        return String.format("Overlap area for rectangles: %s %s = %s squares",
                Arrays.toString(firstRectangleCoordinates),
                Arrays.toString(secondRectangleCoordinates),
                result);
    }
}
