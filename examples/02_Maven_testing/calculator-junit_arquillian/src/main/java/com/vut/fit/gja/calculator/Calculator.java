package com.vut.fit.gja.calculator;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * Calculator class for JUnit testing and Arquillian integration testing
 */
@ApplicationScoped
public class Calculator {
 
    /**
     *
     * @param a first operand
     * @param b second operand
     * @return a + b
     */
    public int add(int a, int b) {
        return a + b;
    }
    
    /**
     *
     * @param a first operand
     * @param b second operand
     * @return a / b
     */
    public int divide(int a, int b) {
        return a / b;
    }
}
