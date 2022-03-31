package com.bombanya.lab2;

import com.bombanya.lab2.logs.Ln;
import com.bombanya.lab2.logs.Log;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;

@RequiredArgsConstructor
@Builder
public class FunctionsSystem implements MathFunction{

    private final Cos cos;
    private final Ln ln;
    private final Log log2;
    private final Log log3;
    private final Log log5;

    @Override
    public BigDecimal calculate(BigDecimal argument, BigDecimal precision) {
        if (precision.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Precision must be greater than 0");

        if (argument.compareTo(BigDecimal.ZERO) <= 0) return cos.calculate(argument, precision);
        else return ln.calculate(argument, precision)
                .add(log3.calculate(argument, precision))
                .multiply(log2.calculate(argument, precision), MathContext.DECIMAL128)
                .multiply(log3.calculate(argument, precision), MathContext.DECIMAL128)
                .add(log5.calculate(argument, precision))
                .multiply(log5.calculate(argument, precision), MathContext.DECIMAL128);
    }
}
