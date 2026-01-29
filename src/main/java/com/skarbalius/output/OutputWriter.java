package com.skarbalius.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputWriter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void write(File outputFile, Output output) {
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(outputFile), output);
        } catch (IOException e) {
            throw new RuntimeException("Error writing output file: " + e.getMessage());
        }
    }
}
