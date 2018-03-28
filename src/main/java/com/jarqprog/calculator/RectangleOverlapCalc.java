package com.jarqprog.calculator;

import java.util.Arrays;

public class RectangleOverlapCalc implements OverlapCalc {

    private final int X_BOTTOM_INDEX = 0;
    private final int Y_BOTTOM_INDEX = 1;
    private final int X_TOP_INDEX = 2;
    private final int Y_TOP_INDEX = 3;

    private int[] firstRectangleCoordinates;
    private int[] secondRectangleCoordinates;
    private long result;

    /*
    Rectangles are made from coordinates:
    [x, y, x', y'] where x,y define bottom corner
    and x',y' define top rectangle's corner
    on the coordinate axis.
    */

    @Override
    public long calculateOverlapArea(int[] firstRectangleCoordinates, int[] secondRectangleCoordinates)
            throws IllegalArgumentException {

        result = 0;
        this.firstRectangleCoordinates = firstRectangleCoordinates;
        this.secondRectangleCoordinates = secondRectangleCoordinates;

        validateArguments();  // throws IllegalArgumentException

        if ( haveRectanglesCommonArea() ) {
            result = calculateOverlappingArea();
        }
        return result;
    }

    private void validateArguments() throws IllegalArgumentException {
        if(firstRectangleCoordinates.length != 4 || secondRectangleCoordinates.length != 4) {
            throw new IllegalArgumentException("Coordinates should contain four integer numbers" +
                    " x,y (left bottom corner), x',y' (right top corner), eg. [-4, 3, 20, 22]");
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

    private long calculateOverlappingArea() {

        int bottomX, bottomY, topX, topY;

        bottomX = getBiggerNumber(firstRectangleCoordinates[X_BOTTOM_INDEX], secondRectangleCoordinates[X_BOTTOM_INDEX]);
        bottomY = getBiggerNumber(firstRectangleCoordinates[Y_BOTTOM_INDEX], secondRectangleCoordinates[Y_BOTTOM_INDEX]);
        topX = getSmallerNumber(firstRectangleCoordinates[X_TOP_INDEX], secondRectangleCoordinates[X_TOP_INDEX]);
        topY = getSmallerNumber(firstRectangleCoordinates[Y_TOP_INDEX], secondRectangleCoordinates[Y_TOP_INDEX]);

        return (topX - bottomX) * (topY - bottomY);
    }

    private int getBiggerNumber(int firstNum, int secondNum) {
        if(firstNum > secondNum) {
            return firstNum;
        }
        return secondNum;
    }

    private int getSmallerNumber(int firstNum, int secondNum) {
        if(firstNum < secondNum) {
            return firstNum;
        }
        return secondNum;
    }

    @Override
    public String toString() {
        return String.format("Overlap area for rectangle%s & rectangle%s = %s squares",
                Arrays.toString(firstRectangleCoordinates),
                Arrays.toString(secondRectangleCoordinates),
                result);
    }
}
