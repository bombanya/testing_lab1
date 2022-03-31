package com.bombanya.lab1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyArccosTest {

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.99, -0.9, -0.5, 0, 0.4, 0.8, 0.95, 1, -0})
    void testInsideDomain(double x){
        assertTrue(Math.abs(MyArccos.calc(x) - Math.acos(x)) < 0.2);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 3, -2, 5, -9})
    void testOutsideDomain(double x){
        assertEquals(Double.NaN, MyArccos.calc(x));
    }
}