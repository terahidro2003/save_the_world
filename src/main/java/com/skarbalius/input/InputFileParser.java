package com.skarbalius.input;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class InputFileParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Input readInputFile(File inputFile) {
        if (!inputFile.exists()) {
            throw new RuntimeException("Input file not found: " + inputFile);
        }

        try {
           return objectMapper.readValue(new FileInputStream(inputFile), Input.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + e.getMessage());
        }
    }

    public static void writeInputFile(File outputFile, Input input) {
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(outputFile), input);
        } catch (IOException e) {
            throw new RuntimeException("Error writing input file: " + e.getMessage());
        }
    }
}
