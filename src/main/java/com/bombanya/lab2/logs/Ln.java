package com.bombanya.lab2.logs;

import com.bombanya.lab2.MathFunction;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.MathContext;

public class Ln implements MathFunction {

    @Override
    public BigDecimal calculate(@NonNull BigDecimal argument, @NonNull BigDecimal precision) {
        if (precision.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Precision must be greater than 0");
        if (argument.compareTo(BigDecimal.ZERO) <= 0)
            throw new ArithmeticException("Ln isn't defined for values less than or equal to zero");

        BigDecimal res = BigDecimal.ZERO;
        for (int i = 1; i < 200 && calcPart(argument, i).abs().compareTo(precision) >= 0; i++) {
            res = res.add(calcPart(argument, i));
        }
        return res.multiply(BigDecimal.valueOf(2));
    }

    private BigDecimal calcPart(BigDecimal argument, int n){
        return argument.subtract(BigDecimal.ONE)
                .divide(argument.add(BigDecimal.ONE), MathContext.DECIMAL128)
                .pow(2*n - 1)
                .divide(BigDecimal.valueOf(2L * n - 1), MathContext.DECIMAL128);
    }
}
