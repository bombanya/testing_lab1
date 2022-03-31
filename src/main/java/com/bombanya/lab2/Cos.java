package com.bombanya.lab2;

import lombok.NonNull;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.bombanya.Utils.bigFactorial;

public class Cos implements MathFunction{

    @Override
    public BigDecimal calculate(@NonNull BigDecimal argument, @NonNull BigDecimal precision) {

        if (precision.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Precision must be greater than 0");

        BigDecimal res = BigDecimal.ZERO;
        for (int i = 0; i < 150 &&
                calcPart(argument, i).abs().compareTo(precision) >= 0; i++) {
            res = res.add(calcPart(argument, i));
        }
        return res;
    }

    private BigDecimal calcPart(BigDecimal argument, int n){
        return BigDecimal.valueOf(Math.pow(-1, n))
                .divide(bigFactorial(2*n), MathContext.DECIMAL128)
                .multiply(argument.pow( 2 * n));
    }
}
