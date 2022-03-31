package com.bombanya.lab2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

class CosTest {

    private final Cos cos = new Cos();
    private final double delta = 0.00001;
    private final BigDecimal precision = BigDecimal.valueOf(0.000001);

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.7, 2.15, PI, 3.75, 3*PI/2, 6, 2*PI})
    void testNormalValues(double argument){
        assertEquals(Math.cos(argument),
                cos.calculate(BigDecimal.valueOf(argument), precision).doubleValue(),
                delta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.7, 2.15, PI, 3.75, 3*PI/2, 6.0, 2*PI})
    void testPeriod(double argument){
        assertEquals(cos.calculate(BigDecimal.valueOf(argument), precision).doubleValue(),
                cos.calculate(BigDecimal.valueOf(argument + 2*PI), precision).doubleValue(),
                delta);
        assertEquals(cos.calculate(BigDecimal.valueOf(argument), precision).doubleValue(),
                cos.calculate(BigDecimal.valueOf(argument - 8*PI), precision).doubleValue(),
                delta);
    }
}