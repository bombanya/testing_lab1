package com.bombanya.lab2;

import com.bombanya.lab2.logs.Ln;
import com.bombanya.lab2.logs.Log;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CsvWriter writer = CsvWriter.builder()
                .function(FunctionsSystem.builder()
                        .cos(new Cos())
                        .ln(new Ln())
                        .log2(new Log(new Ln(), 2))
                        .log3(new Log(new Ln(), 3))
                        .log5(new Log(new Ln(), 5))
                        .build())
                .start(-5)
                .finish(7)
                .step(0.01)
                .filePath("./system.csv")
                .build();
        writer.write();
    }
}
