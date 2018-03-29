package com.jarqprog.calculator;

import java.util.Random;

public class RectangleCoordinatesGenerator implements ShapeCoordinatesGenerator {

    private int maxCoordinateValue;
    private Random random;

    public RectangleCoordinatesGenerator(int maxCoordinateValue) throws InstantiationError {

        int MINIMUM_CORRECT_VALUE = 2;
        int MAXIMUM_CORRECT_VALUE = 999;

        boolean isArgumentInCorrectRange =
                maxCoordinateValue >= MINIMUM_CORRECT_VALUE && maxCoordinateValue <= MAXIMUM_CORRECT_VALUE;

        if(! isArgumentInCorrectRange) {
            throw new InstantiationError(String
                    .format("maxCoordinateValue to generate should be in range %s - %s",
                            MINIMUM_CORRECT_VALUE, MAXIMUM_CORRECT_VALUE));
        }
        this.maxCoordinateValue = maxCoordinateValue;
        random = new Random();
    }

    public int[] generateCoordinates() {
        final int arrayLength = 4;
        int halfArrayLength = arrayLength / 2;
        int[] array = new int[arrayLength];
        for(int i=arrayLength-1; i>=0; i--) {
            int num = random.nextInt(maxCoordinateValue);
            boolean isNumInFirstHalfOfArray = i < halfArrayLength;
            if(isNumInFirstHalfOfArray) {
                boolean isNumberTooBig = num >= array[i + halfArrayLength];
                if(isNumberTooBig) {
                    if(num == 0) {
                        num--;
                    } else {
                        num *= -1;
                    }
                }
            }
            array[i] = num;
        }
        return array;
    }

    public int getMaxCoordinateValue() {
        return maxCoordinateValue;
    }
}
