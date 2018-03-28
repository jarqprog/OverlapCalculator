package com.jarqprog.controller;

import com.jarqprog.calculator.OverlapCalc;
import com.jarqprog.calculator.RectangleOverlapCalc;
import com.jarqprog.calculator.RectangleCoordinatesGenerator;
import com.jarqprog.calculator.ShapeCoordinatesGenerator;
import com.jarqprog.view.View;

public class Root {

    private View view;
    private OverlapCalc calc;

    public static Root getInstance() {
        return new Root();
    }

    private Root() {
        view = new View();
        calc = new RectangleOverlapCalc();
    }

    public void runApp() {
        view.displayMessage(getLineSeparator());
        view.displayMessage("Let's calculate overlap area for randomly generated rectangles.");
        view.displayMessage("Rectangles are made from coordinates:");
        view.displayMessage("[x, y, x', y'] where x,y is bottom left corner");
        view.displayMessage("and x',y' is top right rectangle's corner on the coordinate axis.");

        view.displayMessage("Please specify maximum length of the rectangle's side:");
        int maxSideLen = view.getNumberWithSpecifiedMinimumAndMaximumValue(5, 500);

        ShapeCoordinatesGenerator generator = new RectangleCoordinatesGenerator(maxSideLen/2);

        String userInput = "";
        int[] firstRectangleCoordinates, secondRectangleCoordinates;
        while(! userInput.equals("q")) {

            firstRectangleCoordinates = generator.generateCoordinates();
            secondRectangleCoordinates = generator.generateCoordinates();

            calc.calculateOverlapArea(firstRectangleCoordinates, secondRectangleCoordinates);
            view.displayMessage(calc.toString());
            view.displayMessage(getLineSeparator());

            userInput = view.getUserInput("To quit press 'q'").toLowerCase();
        }
        view.displayMessage(getLineSeparator());
        view.displayMessage("it was great fun, wasn't it? ;)");
    }

    private String getLineSeparator() {
        return "\n--------";
    }
}
