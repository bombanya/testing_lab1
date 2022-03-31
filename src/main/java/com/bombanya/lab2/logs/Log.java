package com.bombanya.lab2.logs;

import com.bombanya.lab2.MathFunction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;

@RequiredArgsConstructor
public class Log implements MathFunction {

    private final Ln ln;
    private final int base;

    @Override
    public BigDecimal calculate(@NonNull BigDecimal argument, @NonNull BigDecimal precision) {
        if (precision.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Precision must be greater than 0");
        if (argument.compareTo(BigDecimal.ZERO) <= 0)
            throw new ArithmeticException("Logs aren't defined for values less than or equal to zero");

        return ln.calculate(argument, precision)
                .divide(ln.calculate(BigDecimal.valueOf(base), precision)
                        , MathContext.DECIMAL128);
    }
}
