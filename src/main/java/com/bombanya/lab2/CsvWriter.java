package com.bombanya.lab2;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Locale;

@RequiredArgsConstructor
@Builder
public class CsvWriter {

    private final MathFunction function;
    private final double start;
    private final double finish;
    private final double step;
    private final String filePath;

    private final BigDecimal precision = BigDecimal.valueOf(0.00000001);

    public void write() throws IOException {
        File file = new File(filePath);
        if (!file.createNewFile()) throw new IOException("File already exists");
        PrintWriter writer = new PrintWriter(file);
        for (double i = start; i <= finish; i += step){
            writer.printf(Locale.UK, "%.5f;%.5f\n", i,
                    function.calculate(BigDecimal.valueOf(i), precision).doubleValue());
        }
        writer.flush();
    }
}
