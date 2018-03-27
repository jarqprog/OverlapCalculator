package com.jarqprog.view;

import java.util.Scanner;

public class View {

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public String getUserInput(String msg) {
        Scanner scanner = new Scanner(System.in);
        displayMessage(msg);
        return scanner.nextLine();
    }

    public int getIntegerFromUser(String msg){
        Scanner scanner = new Scanner(System.in);
        Integer number = null;
        while(number == null) {
            displayMessage(msg);
            try {
                number = scanner.nextInt();
            } catch (NumberFormatException notUsed){
                displayMessage( "- have You type an integer number?");
                number = null;
            }
        }
        return number;
    }
}
