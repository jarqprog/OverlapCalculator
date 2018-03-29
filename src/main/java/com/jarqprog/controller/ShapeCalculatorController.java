package com.jarqprog.controller;

import com.jarqprog.calculator.OverlapCalc;
import com.jarqprog.calculator.ShapeCoordinatesGenerator;
import com.jarqprog.exceptions.IncorrectCoordinatesException;
import com.jarqprog.view.View;

public class ShapeCalculatorController implements CalculatorController {

    private OverlapCalc calc;
    private ShapeCoordinatesGenerator generator;
    private View view;

    public static CalculatorController getInstance(
            OverlapCalc calculator, ShapeCoordinatesGenerator generator, View view) {
        return new ShapeCalculatorController(calculator, generator, view);
    }

    private ShapeCalculatorController(OverlapCalc calc, ShapeCoordinatesGenerator generator, View view) {
        this.calc = calc;
        this.generator = generator;
        this.view = view;
    }

    public void runCalculator() {

        String userInput = "";
        int[] firstRectangleCoordinates, secondRectangleCoordinates;
        while(! userInput.equals("q")) {
            firstRectangleCoordinates = generator.generateCoordinates();
            secondRectangleCoordinates = generator.generateCoordinates();
            try {
                calc.calculateOverlapArea(firstRectangleCoordinates, secondRectangleCoordinates);
                view.displayObject(calc);
            } catch (IncorrectCoordinatesException e) {
                view.displayMessage(e.getMessage());
            }
            userInput = view.getUserInput("To quit press 'q'").toLowerCase();
        }
    }
}
