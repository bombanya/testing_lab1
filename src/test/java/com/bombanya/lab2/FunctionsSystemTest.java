package com.bombanya.lab2;

import com.bombanya.lab2.logs.Ln;
import com.bombanya.lab2.logs.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FunctionsSystemTest {

    @Mock
    private Ln lnMock;
    @Mock
    private Cos cosMock;
    @Mock
    private Log log2Mock;
    @Mock
    private Log log3Mock;
    @Mock
    private Log log5Mock;
    private FunctionsSystem systemWithMocks;

    private final double delta = 0.0001;
    private final BigDecimal precision = BigDecimal.valueOf(0.00000001);

    private double refLog2(double argument){
        return Math.log(argument) / Math.log(2);
    }
    private double refLog3(double argument){
        return Math.log(argument) / Math.log(3);
    }
    private double refLog5(double argument){
        return Math.log(argument) / Math.log(5);
    }

    @BeforeEach
    void init(){
        systemWithMocks = FunctionsSystem.builder()
                .cos(cosMock)
                .ln(lnMock)
                .log2(log2Mock)
                .log3(log3Mock)
                .log5(log5Mock)
                .build();
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.24, -10, 0})
    void testInvocationNumberCos(double argument){
        Mockito.when(cosMock.calculate(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);
        systemWithMocks.calculate(BigDecimal.valueOf(argument), precision);
        Mockito.verify(cosMock, Mockito.times(1))
                .calculate(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.001, 1, 12, 42})
    void testInvocationNumberLogs(double argument) {
        Mockito.when(lnMock.calculate(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);
        Mockito.when(log2Mock.calculate(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);
        Mockito.when(log3Mock.calculate(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);
        Mockito.when(log5Mock.calculate(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);
        systemWithMocks.calculate(BigDecimal.valueOf(argument), precision);
        Mockito.verify(lnMock, Mockito.times(1))
                .calculate(Mockito.any(), Mockito.any());
        Mockito.verify(log2Mock, Mockito.times(1))
                .calculate(Mockito.any(), Mockito.any());
        Mockito.verify(log3Mock, Mockito.times(2))
                .calculate(Mockito.any(), Mockito.any());
        Mockito.verify(log5Mock, Mockito.times(2))
                .calculate(Mockito.any(), Mockito.any());
        Mockito.verify(cosMock, Mockito.never()).calculate(Mockito.any(), Mockito.any());
    }

    @ParameterizedTest
    @CsvSource({
            "0.1212, 32.6316",
            "0.55, 0.337082",
            "0.7, 0.0743382",
            "1, 0",
            "1.5, 0.105595",
            "2.3, 1.01795",
            "5, 11.4578047",
            "17.123, 104.57017"
    })
    void testUnitCalcsLogs(double argument, double result){
        Mockito.when(lnMock.calculate(Mockito.any(), Mockito.any()))
                        .thenAnswer(invocationOnMock ->
                                BigDecimal.valueOf(Math.log(
                                        invocationOnMock.<BigDecimal>getArgument(0).doubleValue())));
        Mockito.when(log2Mock.calculate(Mockito.any(), Mockito.any()))
                        .thenAnswer(invocationOnMock ->
                                BigDecimal.valueOf(refLog2(
                                        invocationOnMock.<BigDecimal>getArgument(0).doubleValue())));
        Mockito.when(log3Mock.calculate(Mockito.any(), Mockito.any()))
                        .thenAnswer(invocationOnMock ->
                                BigDecimal.valueOf(refLog3(
                                        invocationOnMock.<BigDecimal>getArgument(0).doubleValue())));
        Mockito.when(log5Mock.calculate(Mockito.any(), Mockito.any()))
                        .thenAnswer(invocationOnMock ->
                                BigDecimal.valueOf(refLog5(
                                        invocationOnMock.<BigDecimal>getArgument(0).doubleValue())));
        assertEquals(result, systemWithMocks.calculate(BigDecimal.valueOf(argument), precision).doubleValue(),
                delta);
    }

    @ParameterizedTest
    @CsvSource({
            "-1.12, 0.4356824",
            "-10.0, -0.83907",
            "0, 1"
    })
    void testUnitCalcsCos(double argument, double result){
        Mockito.when(cosMock.calculate(Mockito.any(), Mockito.any()))
                .thenAnswer(invocationOnMock ->
                        BigDecimal.valueOf(Math.cos(
                                invocationOnMock.<BigDecimal>getArgument(0).doubleValue())));
        assertEquals(result,
                systemWithMocks.calculate(BigDecimal.valueOf(argument), precision).doubleValue(),
                delta);
    }

    @ParameterizedTest
    @CsvSource({
            "-1.12, 0.4356824",
            "-10.0, -0.83907",
            "0, 1",
            "0.1212, 32.6316",
            "0.55, 0.337082",
            "0.7, 0.0743382",
            "1, 0",
            "1.5, 0.105595",
            "2.3, 1.01795",
            "5, 11.4578047",
            "17.123, 104.57017"
    })
    void testIntegrationCalcs(double argument, double result){
        var system = FunctionsSystem.builder()
                .cos(new Cos())
                .ln(new Ln())
                .log2(new Log(new Ln(), 2))
                .log3(new Log(new Ln(), 3))
                .log5(new Log(new Ln(), 5))
                .build();
        assertEquals(result,
                system.calculate(BigDecimal.valueOf(argument), precision).doubleValue(),
                delta);
    }

    @Test
    void printTestCsv() throws IOException {
        var system = FunctionsSystem.builder()
                .cos(new Cos())
                .ln(new Ln())
                .log2(new Log(new Ln(), 2))
                .log3(new Log(new Ln(), 3))
                .log5(new Log(new Ln(), 5))
                .build();
        CsvWriter.builder()
                .function(system)
                .start(-3)
                .finish(7)
                .step(0.01)
                .filePath("systemTest.csv")
                .build()
                .write();
    }
}