package com.bombanya;

import java.math.BigDecimal;

public class Utils {

    public static long factorial(int n){
        if (n < 0) return -1;
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public static BigDecimal bigFactorial(int n){
        if (n < 0) return new BigDecimal("-1");
        BigDecimal res = BigDecimal.ONE;
        for (int i = 1; i <= n; i++){
            res = res.multiply(new BigDecimal(i));
        }
        return res;
    }

}
