package com.vut.fit.gja.calculator;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * injects the Calculator as reusable component, can be used by many possible
 * components such as Student, Proffesor, Internet browser... but only one
 * shared instance per scope for all possible components using Calculator
 *
 */
@ApplicationScoped
public class Student {

    @Inject
    private Calculator calculator;

    /**
     * The student uses the injected Calculator to calculate return value.
     *
     * @param a first operand
     * @param b second operand
     * @return student answer of a + b, in format "The answer is {x+y}"
     */
    public String answerAddition(int a, int b) {

        // let the student answer the question using injected Calculator
        int result = calculator.add(a, b);
        return "The answer is " + result;
    }
}
