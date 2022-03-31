package com.bombanya.lab2.logs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LnTest {

    private final Ln ln = new Ln();
    private final double delta = 0.00001;
    private final BigDecimal precision = BigDecimal.valueOf(0.0000001);

    @ParameterizedTest
    @ValueSource(doubles = {0.03, 0.4, 1, 4, 14.75, 50.01})
    void testInDomain(double argument){
        assertEquals(ln.calculate(BigDecimal.valueOf(argument), precision).doubleValue(),
                Math.log(argument), delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -100})
    void testOutsideDomain(double argument){
        assertThrows(ArithmeticException.class, () ->
                ln.calculate(BigDecimal.valueOf(argument), precision));
    }
}