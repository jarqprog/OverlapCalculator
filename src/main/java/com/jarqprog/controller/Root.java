package com.jarqprog.controller;

import com.jarqprog.models.OverlapCalc;
import com.jarqprog.models.OverlapCalcImpl;
import com.jarqprog.models.RandomIntegerArrayGenerator;
import com.jarqprog.view.View;

public class Root {

    private View view;
    private OverlapCalc calc;
    private RandomIntegerArrayGenerator generator;

    public static Root getInstance() {
        return new Root();
    }

    private Root() {
        view = new View();
        calc = new OverlapCalcImpl();
        int defaultArrayLen = 4;
        int defaultMaxValueToGenerate = 400;
        generator = new RandomIntegerArrayGenerator(defaultArrayLen, defaultMaxValueToGenerate);
    }

    public void runApp() {
        view.displayMessage(getLineSeparator());
        view.displayMessage("Let's calculate overlap area for randomly generated rectangles.");
        view.displayMessage("Rectangles are made from coordinates:");
        view.displayMessage("[x, y, x', y'] where x,y is bottom left corner");
        view.displayMessage("and x',y' is top right rectangle's corner on the coordinate axis.");
        String userInput = "";
        int[] firstRectangleCoordinates, secondRectangleCoordinates;
        while(! userInput.equals("q")) {
            firstRectangleCoordinates = generator.generateArray();
            secondRectangleCoordinates = generator.generateArray();
            calc.calculateOverlapArea(firstRectangleCoordinates, secondRectangleCoordinates);
            view.displayMessage(calc.toString());
            view.displayMessage(getLineSeparator());
            userInput = view.getUserInput("To quit press 'q'").toLowerCase();
        }
        view.displayMessage("it was great fun, wasn't it? ;)");
    }

    private String getLineSeparator() {
        return "\n--------";
    }
}
