package com.jarqprog.controller;

import com.jarqprog.calculator.OverlapCalc;
import com.jarqprog.calculator.RectangleOverlapCalc;
import com.jarqprog.calculator.RectangleCoordinatesGenerator;
import com.jarqprog.calculator.ShapeCoordinatesGenerator;
import com.jarqprog.view.View;

public class Root {

    private View view;

    public static Root getInstance() {
        return new Root();
    }

    private Root() {
        view = new View();
    }

    public void runApp() {

        executeIntroduction();

        CalculatorController controller = createCalculatorController();
        controller.runCalculator();

        executeOutro();
    }

    private void executeIntroduction() {
        view.displaySeparatingLine();
        view.displayMessage("Let's calculate overlap area for randomly generated rectangles.");
        view.displayMessage("Rectangles are made from coordinates:");
        view.displayMessage("[x, y, x', y'] where x,y is bottom left corner");
        view.displayMessage("and x',y' is top right rectangle's corner on the coordinate axis.");
        view.displaySeparatingLine();
    }

    private CalculatorController createCalculatorController() {

        OverlapCalc calculator = new RectangleOverlapCalc();
        ShapeCoordinatesGenerator generator = createGenerator();
        return ShapeCalculatorController.getInstance(calculator, generator, view);
    }

    private ShapeCoordinatesGenerator createGenerator() {
        view.displayMessage("Please specify maximum length of the rectangle's side:");
        int maxSideLen = view.getNumberWithSpecifiedMinimumAndMaximumValue(5, 500);
        return new RectangleCoordinatesGenerator(maxSideLen/2);
    }

    private void executeOutro() {
        view.displaySeparatingLine();
        view.displayMessage("it was great fun, wasn't it? ;)");
    }
}
