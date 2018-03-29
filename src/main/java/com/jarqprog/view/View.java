package com.jarqprog.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public void displaySeparatingLine() {
        System.out.println("\n------------------");
    }

    public String getUserInput(String msg) {
        Scanner scanner = new Scanner(System.in);
        displayMessage(msg);
        return scanner.nextLine();
    }

    public <T> void displayObject(T object) {
        System.out.println(object.toString());
    }

    public int getNumberWithSpecifiedMinimumAndMaximumValue(int minimumAcceptableValue, int maximumAcceptableValue){
        Scanner scanner = new Scanner(System.in);
        Integer number = 0;
        boolean isValueCorrect = false;

        while(! isValueCorrect) {
            displayMessage(String
                    .format("enter the number in the range %s - %s", minimumAcceptableValue, maximumAcceptableValue));
            try {
                number = scanner.nextInt();
                isValueCorrect = number >= minimumAcceptableValue && number <= maximumAcceptableValue;

                if(! isValueCorrect) {
                    displayMessage( "- value isn't within given range");
                }
            } catch (NumberFormatException | InputMismatchException notUsed) {
                displayMessage( "- have You type an integer number?");
                scanner = new Scanner(System.in);
                number = 0;
            }
        }
        return number;
    }
}
