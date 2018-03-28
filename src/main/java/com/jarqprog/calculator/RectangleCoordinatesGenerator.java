package com.jarqprog.models;

import java.util.Random;

public class RectangleCoordinatesGenerator implements ShapeCoordinatesGenerator {

    private int maxValueToGenerate;
    private Random random;

    public RectangleCoordinatesGenerator(int maxValueToGenerate) throws InstantiationError {
        if(maxValueToGenerate >= 1000) {
            throw new InstantiationError("Max value of number to add to array is to big (value should be lower than million).");
        }
        this.maxValueToGenerate = maxValueToGenerate;
        random = new Random();
    }

    public int[] generateCoordinates() {
        final int arrayLength = 4;
        int halfArrayLength = arrayLength / 2;
        int[] array = new int[arrayLength];
        for(int i=arrayLength-1; i>=0; i--) {
            int num = random.nextInt(maxValueToGenerate);
            boolean isNumInFirstHalfOfArray = i < halfArrayLength;
            if(isNumInFirstHalfOfArray) {
                boolean isNumberTooBig = num >= array[i + halfArrayLength];
                if(isNumberTooBig) {
                    num++;
                    num *= -1;
                }
            }
            array[i] = num;
        }
        return array;
    }
}
