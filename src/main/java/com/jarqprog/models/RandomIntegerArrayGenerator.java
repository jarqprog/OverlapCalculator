package com.jarqprog.models;

import java.util.Arrays;
import java.util.Random;

public class RandomIntegerArrayGenerator {

    private int arrayLength;
    private int maxValue;
    private int[] array;
    private Random random;

    public RandomIntegerArrayGenerator(int arrayLength, int maxValue) throws InstantiationError {
        if(arrayLength >= 1000000) {
            throw new InstantiationError("Array length is to big! Use length less than a million.");
        }
        if(maxValue >= 1000000) {
            throw new InstantiationError("Max value of number to add to array is to big (value should be lower than million).");
        }
        this.arrayLength = arrayLength;
        this.maxValue = maxValue;
        array = new int[arrayLength];
        random = new Random();
    }

    public int[] generateArray() {
        for(int i=0; i<arrayLength; i++) {
            array[i] = random.nextInt(maxValue);
        }
        System.out.println("array:    "  + Arrays.toString(array));
        return array;
    }

    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
