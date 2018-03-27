package com.jarqprog.models;

import java.util.Arrays;

public class OverlapCalcImpl implements OverlapCalc {

    private final int xIndex = 0;
    private final int yIndex = 1;

    // rectangle's corners coordinates [x,y]:
    private int[] firstRectangleBottomLeft;
    private int[] secondRectangleBottomLeft;
    private int[] firstRectangleTopRight;
    private int[] secondRectangleTopRight;
    private long result;

    @Override
    public long calculateOverlapArea(int[] firstRectangleCoordinates, int[] secondRectangleCoordinates)
            throws IllegalArgumentException {
        result = 0;
        validateArguments(firstRectangleCoordinates, secondRectangleCoordinates);
        parseArguments(firstRectangleCoordinates, secondRectangleCoordinates);
        if ( haveRectanglesCommonArea() ) {
            result = calculateOverlappingArea();
        }
        return result;
    }

    private void validateArguments(int[] firstRectangleCoordinates, int[] secondRectangleCoordinates)
            throws IllegalArgumentException {  // should return bool? (throws exception instead)
        if(firstRectangleCoordinates.length != 4 || secondRectangleCoordinates.length != 4) {
            throw new IllegalArgumentException("Coordinates should contain four integer numbers" +
                    " x,y (left bottom corner), x',y' (right top corner), eg. [-4, 3, 20, 22]");
        }
    }

    private void parseArguments(int[] firstRectangleCoordinates, int[] secondRectangleCoordinates) {

        int startBottomIndex = 0;
        int finalBottomIndex = 2;
        int finalTopIndex = 4;
        firstRectangleBottomLeft = Arrays.copyOfRange(firstRectangleCoordinates, startBottomIndex, finalBottomIndex);
        secondRectangleBottomLeft = Arrays.copyOfRange(secondRectangleCoordinates, startBottomIndex, finalBottomIndex);
        firstRectangleTopRight = Arrays.copyOfRange(firstRectangleCoordinates, finalBottomIndex, finalTopIndex);
        secondRectangleTopRight = Arrays.copyOfRange(secondRectangleCoordinates, finalBottomIndex, finalTopIndex);
    }

    private boolean haveRectanglesCommonArea() {

        boolean hasFirstPokemonCorrectParameters =
                firstRectangleBottomLeft[xIndex] < firstRectangleTopRight[xIndex] &&
                        firstRectangleBottomLeft[yIndex] < firstRectangleTopRight[yIndex];
        boolean hasSecondPokemonCorrectParameters =
                secondRectangleBottomLeft[xIndex] < secondRectangleTopRight[xIndex] &&
                        secondRectangleBottomLeft[yIndex] < secondRectangleTopRight[yIndex];
        boolean firstOverlapCondition =
                secondRectangleBottomLeft[xIndex] < firstRectangleTopRight[xIndex] &&
                        secondRectangleBottomLeft[yIndex] < firstRectangleTopRight[yIndex];
        boolean secondOverlapCondition =
                secondRectangleTopRight[xIndex] > firstRectangleBottomLeft[xIndex] &&
                        secondRectangleTopRight[yIndex] > firstRectangleBottomLeft[yIndex];

        return hasFirstPokemonCorrectParameters && hasSecondPokemonCorrectParameters &&
                firstOverlapCondition && secondOverlapCondition;
    }

    @Override
    public String toString() {
        return String.format("Overlap area for: [%s,%s] [%s,%s] = %s",
                Arrays.toString(firstRectangleBottomLeft),
                Arrays.toString(secondRectangleBottomLeft),
                Arrays.toString(firstRectangleTopRight),
                Arrays.toString(secondRectangleTopRight),
                result);
    }

    private long calculateOverlappingArea() {

        int bottomX, bottomY, topX, topY;

        bottomX = getBiggerNumber(firstRectangleBottomLeft[xIndex], secondRectangleBottomLeft[xIndex]);
        bottomY = getBiggerNumber(firstRectangleBottomLeft[yIndex], secondRectangleBottomLeft[yIndex]);
        topX = getSmallerNumber(firstRectangleTopRight[xIndex], secondRectangleTopRight[xIndex]);
        topY = getSmallerNumber(firstRectangleTopRight[yIndex], secondRectangleTopRight[yIndex]);

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
}
