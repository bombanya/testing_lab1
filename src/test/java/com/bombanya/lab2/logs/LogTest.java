package com.bombanya.lab2.logs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogTest {

    @Mock
    private Ln lnMock;
    private final double delta = 0.0001;
    private final BigDecimal precision = BigDecimal.valueOf(0.000001);

    @Test
    void testInvocationNumber(){
        Mockito.when(lnMock.calculate(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);
        Log log = new Log(lnMock, 3);
        log.calculate(BigDecimal.valueOf(45), precision);
        Mockito.verify(lnMock, Mockito.times(2))
                .calculate(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0.0001",
            "-0.002, 0.0001",
            "12, 0"
    })
    void testExceptions(double argument, double precision){
        Log log = new Log(lnMock, 2);
        assertThrows(Exception.class, () -> log.calculate(BigDecimal.valueOf(argument)
                , BigDecimal.valueOf(precision)));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 0.234, -2.095419",
            "2, 2.4, 1.2630344",
            "3, 4.75, 1.41828435",
            "3, 10.5678, 2.1461726",
            "5, 0.01, -2.8613531161",
            "5, 45.18972, 2.3678264305"
    })
    void testUnitCalcs(int base, double argument, double result){
        Mockito.when(lnMock.calculate(Mockito.any(), Mockito.any()))
                .thenAnswer(invocationOnMock
                        -> BigDecimal.valueOf(Math.log(
                                invocationOnMock.<BigDecimal>getArgument(0).doubleValue())));
        Log log = new Log(lnMock, base);
        assertEquals(result,
                log.calculate(BigDecimal.valueOf(argument), precision).doubleValue(),
                delta);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 0.234, -2.095419",
            "2, 2.4, 1.2630344",
            "3, 4.75, 1.41828435",
            "3, 10.5678, 2.1461726",
            "5, 0.01, -2.8613531161",
            "5, 45.18972, 2.3678264305"
    })
    void testIntegrationCalcs(int base, double argument, double result){
        Log log = new Log(new Ln(), base);
        assertEquals(result,
                log.calculate(BigDecimal.valueOf(argument), precision).doubleValue(),
                delta);
    }

}